package com.simon.basic.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simon.basic.core.Failure


/**
 * @author Simon
 * @date 2020/4/12
 * @desc Base ViewModel class with default Failure handling.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}