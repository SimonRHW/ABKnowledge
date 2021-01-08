@file:Suppress("UNCHECKED_CAST")

package com.simon.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simon.news.features.NewsViewModel
import com.simon.news.model.NewsRepository
import com.simon.news.model.RemoteNewsDataSourceImpl

/**
 * @author Simon
 * @date 2020/9/19
 * @desc ViewModel构造工厂，为了统一管理viewModel的初始化构建
 */
object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(
                NewsRepository(
                    RemoteNewsDataSourceImpl()
                )
            )
            else -> throw IllegalArgumentException("not find ViewModel class: ${modelClass.name}")
        }
    } as T
}