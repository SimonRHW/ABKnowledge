package com.simon.recent.features.recycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.simon.imageload.GlideImageLoader
import com.simon.recent.features.R
import com.simon.recent.features.databinding.AnimalConcatRowBinding
import com.simon.recent.features.databinding.AnimalsRowBinding
import com.simon.recent.features.databinding.BreedsRowBinding

data class Animal(val name: String, val image: String)

data class Breed(val name: String)

abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T, position: Int)
}

abstract class BaseConcatHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(adapter: T)
}

class AnimalAdapter(private val context: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var animalList = mutableListOf<Animal>()

    companion object {
        const val ITEMS_PER_PAGE = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemWidth: Int = parent.width / ITEMS_PER_PAGE
        val binding = AnimalsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutParams: ViewGroup.LayoutParams = binding.root.layoutParams
        layoutParams.width = itemWidth
        binding.root.layoutParams = layoutParams
        return AnimalViewHolder(binding)
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

    inner class AnimalViewHolder(private val binding: AnimalsRowBinding) :
        BaseViewHolder<Animal>(binding) {

        override fun bind(item: Animal, position: Int) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_pic_error)
                .priority(Priority.HIGH)
            GlideImageLoader(
                binding.animalImage,
                null
            ).load(item.image, options)
            binding.animalName.text = item.name
        }
    }
}

class DogBreedAdapter(
    private val context: Context
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var dogBreedList = mutableListOf<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return DogBreedViewHolder(
            BreedsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class DogBreedViewHolder(private val binding: BreedsRowBinding) :
        BaseViewHolder<Breed>(binding) {
        override fun bind(item: Breed, position: Int) {
            binding.breedName.text = item.name
        }
    }
}

class BaseGridConcatAdapter(
    private val context: Context,
    private val animalAdapter: AnimalAdapter,
    private val spanCount: Int
) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val binding =
            AnimalConcatRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvAnimalConcat.layoutManager = GridLayoutManager(context, spanCount)
        return ConcatViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(animalAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(private val binding: AnimalConcatRowBinding) :
        BaseConcatHolder<AnimalAdapter>(binding) {
        override fun bind(adapter: AnimalAdapter) {
            binding.rvAnimalConcat.adapter = adapter
        }
    }
}