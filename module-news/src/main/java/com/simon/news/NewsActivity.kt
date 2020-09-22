package com.simon.news

import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.basic.core.mvvm.ViewModelActivity
import com.simon.news.databinding.ActivityNewsBinding


@Route(path = "/module/news_home")
class NewsActivity : ViewModelActivity<ActivityNewsBinding, NewsViewModel>() {

    override fun obtainViewModel(): NewsViewModel {
        TODO("Not yet implemented")
    }

    override fun layoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun initWidget() {
        TODO("Not yet implemented")
    }

    override fun bind(bind: ActivityNewsBinding, viewModel: NewsViewModel) {
        TODO("Not yet implemented")
    }

}