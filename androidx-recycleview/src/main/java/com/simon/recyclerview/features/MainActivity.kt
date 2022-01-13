package com.simon.recyclerview.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.simon.recyclerview.features.databinding.ActivityMainBinding
import com.simon.recyclerview.features.recycleView.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animalAdapter = AnimalAdapter(this)
        animalAdapter.setAnimals(
            mutableListOf(
                Animal(
                    "Dog",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.baa.bitautotech.com%2Fimg%2Fv2img3.baa.bitautotech.com%2Fusergroup%2Fday_130203%2F20130203_a3e772c3-f97f-4f4c-8168-6aaa847bee8b_990_0_max_jpg.jpg&refer=http%3A%2F%2Fimg3.baa.bitautotech.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611217572&t=55844713948896846ef17ce1107e9b5c"
                ),
                Animal(
                    "Cat",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.baa.bitautotech.com%2Fimg%2Fv2img3.baa.bitautotech.com%2Fusergroup%2Fday_130203%2F20130203_a3e772c3-f97f-4f4c-8168-6aaa847bee8b_990_0_max_jpg.jpg&refer=http%3A%2F%2Fimg3.baa.bitautotech.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611217572&t=55844713948896846ef17ce1107e9b5c"
                ),
                Animal(
                    "Hamster",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.baa.bitautotech.com%2Fimg%2Fv2img3.baa.bitautotech.com%2Fusergroup%2Fday_130203%2F20130203_a3e772c3-f97f-4f4c-8168-6aaa847bee8b_990_0_max_jpg.jpg&refer=http%3A%2F%2Fimg3.baa.bitautotech.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611217572&t=55844713948896846ef17ce1107e9b5c"
                ),
                Animal(
                    "Shark",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.baa.bitautotech.com%2Fimg%2Fv2img3.baa.bitautotech.com%2Fusergroup%2Fday_130203%2F20130203_a3e772c3-f97f-4f4c-8168-6aaa847bee8b_990_0_max_jpg.jpg&refer=http%3A%2F%2Fimg3.baa.bitautotech.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611217572&t=55844713948896846ef17ce1107e9b5c"
                )
            )
        )
        val breedAdapter = DogBreedAdapter(this)
        breedAdapter.setBreeds(
            mutableListOf(
                Breed("Bulldog"),
                Breed("Beagle"),
                Breed("Bulldog"),
                Breed("Golden retriever")
            )
        )
        val concatAdapter =
            ConcatAdapter(breedAdapter, BaseGridConcatAdapter(this, animalAdapter, 4))
        binding.recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycleView.adapter = concatAdapter
    }
}