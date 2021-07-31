package com.simon.news.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simon.basic.core.functional.Failure
import com.simon.basic.core.mvvm.BaseViewModel
import com.simon.news.model.NewsRepository
import com.simon.news.model.bean.News
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * @author Simon
 * @date 2020/9/19
 * @desc
 */

interface PaymentRecordsState {

    fun newsList(): LiveData<List<News>>

    fun loadingState(): LiveData<Boolean>

    fun errorState(): LiveData<Throwable>
}

class NewsViewModel(private val repository: NewsRepository) : BaseViewModel() {

    private val newsList = MutableLiveData<List<News>>()

    fun getNewsList(type: String) {
        repository.getNewsList(type)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<News>?> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<News>) {
                    newsList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    failure.postValue(Failure.NetworkConnection)
                }
            })
    }

}