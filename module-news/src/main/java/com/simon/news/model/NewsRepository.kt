package com.simon.news.model

import com.simon.news.model.bean.News
import io.reactivex.Single

class NewsRepository(val dataSource: NewsDataSource) : NewsDataSource {
    override fun getNewsList(type: String): Single<List<News>> {
        return dataSource.getNewsList(type)
    }
}