package com.simon.news.model

import com.simon.news.model.bean.News
import com.simon.news.network.JHApi
import com.simon.news.network.RequestManager
import io.reactivex.Single

interface NewsDataSource {
    fun getNewsList(type: String): Single<List<News>>
}

class RemoteNewsDataSourceImpl() : NewsDataSource {

    override fun getNewsList(type: String): Single<List<News>> {
        val params = HashMap<String, String>()
        params["key"] = JHApi.JU_HE_NEWS_KEY
        params["type"] = type
        return RequestManager().createRequest(JHApi::class.java)
            .getNewsList(params)
            .map { it.convert() }
            .map { it.data }
    }

}