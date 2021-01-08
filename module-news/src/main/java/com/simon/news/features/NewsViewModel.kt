package com.simon.news.features

import androidx.lifecycle.MutableLiveData
import com.simon.basic.core.functional.Failure
import com.simon.basic.core.mvvm.BaseViewModel
import com.simon.news.model.NewsRepository
import com.simon.news.model.bean.Data
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * @author Simon
 * @date 2020/9/19
 * @desc
 */
class NewsViewModel(private val repository: NewsRepository) : BaseViewModel() {

    val newList = MutableLiveData<List<Data>>()

    fun getNewsList(type: String) {
        repository.getNewsList(type)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Data>?> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<Data>) {
                    newList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    failure.postValue(Failure.NetworkConnection)
                }
            })
    }

}