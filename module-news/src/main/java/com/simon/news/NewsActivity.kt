package com.simon.news

import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.basic.core.mvvm.ViewModelActivity
import com.simon.news.databinding.ActivityNewsBinding

@Route(path = "/module/news_home")
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

    }

}