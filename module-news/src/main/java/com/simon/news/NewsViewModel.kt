package com.simon.news

import androidx.databinding.ObservableField
import com.simon.basic.core.mvvm.BaseViewModel

/**
 * @author Simon
 * @date 2020/9/19
 * @desc
 */
class NewsViewModel : BaseViewModel() {

    val text = ObservableField("init")

}