package com.simon.news.network

import com.simon.basic.core.bean.DataResp
import com.simon.news.model.bean.JHData
import com.simon.news.model.bean.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JHApi {

    companion object {
        const val JU_HE_HOST = "：http://v.juhe.cn"
        const val JU_HE_NEWS_KEY = "55c467010983016e89b59eaa22edeef5"

        const val NEWS_URL = "/toutiao/index"
    }

    // 获取路线详情
    @GET(NEWS_URL)
    fun getNewsList(
        @QueryMap queryMap: HashMap<String, String>
    ): Single<DataResp<JHData<News>>>

}