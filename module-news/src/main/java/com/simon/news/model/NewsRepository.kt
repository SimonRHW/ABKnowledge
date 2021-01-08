package com.simon.news.model

import com.simon.news.model.bean.Data
import io.reactivex.Single

class NewsRepository(private val dataSource: NewsDataSource) : NewsDataSource {
    override fun getNewsList(type: String): Single<List<Data>> {
        return dataSource.getNewsList(type)
    }
}