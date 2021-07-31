package com.simon.news.network

import com.simon.netcore.AbstractNetworkManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

open class RequestManager : AbstractNetworkManager() {

    override fun createClient(): OkHttpClient {
        return buildOkHttpClient()
    }

    override fun getBaseUrl(): String {
        return JHApi.JU_HE_HOST
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        return builder.build()
    }
}