package com.simon.news.features

import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.basic.core.mvvm.ViewModelActivity
import com.simon.module.manager.ModuleRouteManager
import com.simon.news.R
import com.simon.news.ViewModelFactory
import com.simon.news.databinding.ActivityNewsBinding
import com.simon.news.model.NewsRepository
import com.simon.news.model.RemoteNewsDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        val newsList = NewsRepository(RemoteNewsDataSourceImpl()).getNewsList("")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}