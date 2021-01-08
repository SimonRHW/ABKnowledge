package com.simon.recent.features.recycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.simon.imageload.GlideImageLoader
import com.simon.recent.features.R
import kotlinx.android.synthetic.main.animal_concat_row.view.*
import kotlinx.android.synthetic.main.animals_row.view.*
import kotlinx.android.synthetic.main.breeds_row.view.*

data class Animal(val name: String, val image: String)

data class Breed(val name: String)

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
}

abstract class BaseConcatHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}

class AnimalAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var animalList = mutableListOf<Animal>()

    companion object {
        const val ITEMS_PER_PAGE = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemWidth: Int = parent.width / ITEMS_PER_PAGE
        val view = LayoutInflater.from(context).inflate(R.layout.animals_row, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.width = itemWidth
        view.layoutParams = layoutParams
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (animalList.size > 0) animalList.size else 0
    }

    fun setAnimals(animalList: MutableList<Animal>) {
        this.animalList = animalList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Animal {
        return animalList[position]
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val animalObj = animalList[position]
        when (holder) {
            is AnimalViewHolder -> holder.bind(animalObj, position)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class AnimalViewHolder(itemView: View) : BaseViewHolder<Animal>(itemView) {
        override fun bind(item: Animal, position: Int) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_pic_error)
                .priority(Priority.HIGH)
            GlideImageLoader(
                itemView.animal_image,
                null
            ).load(item.image, options)
            itemView.animal_name.text = item.name
        }
    }
}

class DogBreedAdapter(
    private val context: Context
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var dogBreedList = mutableListOf<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return DogBreedViewHolder(
            LayoutInflater.from(context).inflate(R.layout.breeds_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dogBreedList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DogBreedViewHolder -> holder.bind(
                dogBreedList[position],
                position
            )
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    fun setBreeds(dogBreedList: MutableList<Breed>) {
        this.dogBreedList = dogBreedList
        notifyDataSetChanged()
    }

    inner class DogBreedViewHolder(itemView: View) : BaseViewHolder<Breed>(itemView) {
        override fun bind(item: Breed, position: Int) {
            itemView.breed_name.text = item.name
        }
    }
}

class BaseGridConcatAdapter(
    private val context: Context,
    private val animalAdapter: AnimalAdapter,
    private val spanCount: Int
) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.animal_concat_row, parent, false)
        view.rv_animal_concat.layoutManager = GridLayoutManager(context, spanCount)
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(animalAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(itemView: View) : BaseConcatHolder<AnimalAdapter>(itemView) {
        override fun bind(adapter: AnimalAdapter) {
            itemView.rv_animal_concat.adapter = adapter
        }
    }
}