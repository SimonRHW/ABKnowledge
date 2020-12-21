package com.simon.netcore

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Simon
 * @date
 * @desc
 */
abstract class AbstractNetworkManager {



    @Volatile
    private var mOkHttpClient: OkHttpClient? = null

    abstract fun createClient(): OkHttpClient

    abstract fun getBaseUrl(): String

    open fun getClient(): OkHttpClient {
        if (this.mOkHttpClient == null) {
            synchronized(this) {
                if (this.mOkHttpClient == null) {
                    this.mOkHttpClient = createClient()
                }
            }
        }
        return this.mOkHttpClient!!
    }

    open fun <T> createRequest(cls: Class<T>): T {
        return createRequest(cls, getBaseUrl())
    }

    open fun <T> createRequest(cls: Class<T>, baseUrl: String): T {
        return Retrofit.Builder().baseUrl(baseUrl).client(getClient())
            .addConverterFactory(GsonConverterFactory.create()).build().create(cls)
    }

}