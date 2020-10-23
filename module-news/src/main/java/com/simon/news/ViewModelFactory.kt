@file:Suppress("UNCHECKED_CAST")

package com.simon.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Simon
 * @date 2020/9/19
 * @desc ViewModel构造工厂，为了统一管理viewModel的初始化构建
 */
object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel()
            else -> throw IllegalArgumentException("not find ViewModel class: ${modelClass.name}")
        }
    } as T
}