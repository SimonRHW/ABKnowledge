package com.simon.news.features

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.basic.core.mvvm.ViewModelActivity
import com.simon.module.manager.ModuleRouteManager
import com.simon.news.R
import com.simon.news.ViewModelFactory
import com.simon.news.databinding.ActivityNewsBinding

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
        mBinding.recycleView.layoutManager = LinearLayoutManager(this@NewsActivity)
    }

    override fun onStart() {
        super.onStart()
        mViewModel.getNewsList("")
    }

}