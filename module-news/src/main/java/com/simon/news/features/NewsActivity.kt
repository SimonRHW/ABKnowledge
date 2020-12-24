package com.simon.news.features

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.basic.core.mvvm.ViewModelActivity
import com.simon.module.manager.ModuleRouteManager
import com.simon.news.R
import com.simon.news.ViewModelFactory
import com.simon.news.databinding.ActivityNewsBinding
import com.simon.news.model.NewsRepository
import com.simon.news.model.RemoteNewsDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_news.*

@Route(path = ModuleRouteManager.NEWS_HOME_ROUTE)
class NewsActivity : ViewModelActivity<ActivityNewsBinding, NewsViewModel>() {

    override fun obtainViewModel(): NewsViewModel {
        return ViewModelProvider(this, ViewModelFactory).get(NewsViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_news
    }

    override fun bind(bind: ActivityNewsBinding, viewModel: NewsViewModel) {
        bind.vm = viewModel
    }

    override fun initWidget() {
        recycleView.layoutManager = LinearLayoutManager(this@NewsActivity)

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
//        val concatAdapter = ConcatAdapter(animalAdapter, breedAdapter)
        recycleView.layoutManager = LinearLayoutManager(this@NewsActivity)
        recycleView.adapter = concatAdapter

    }

    override fun onStart() {
        super.onStart()
        val newsList = NewsRepository(RemoteNewsDataSourceImpl()).getNewsList("")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}