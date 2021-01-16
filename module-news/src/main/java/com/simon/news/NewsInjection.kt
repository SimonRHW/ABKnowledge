package com.simon.news

import com.simon.news.model.NewsRepository
import com.simon.news.model.RemoteNewsDataSourceImpl

object NewsInjection {

    fun provideNewsRepository() = NewsRepository(RemoteNewsDataSourceImpl())


}