package com.simon.basic.core.mvp

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
interface IPresenter {

    var attachView: IView?

    fun subscribe()

    fun unsubscribe()

    fun detachView() {
        attachView = null
    }

}