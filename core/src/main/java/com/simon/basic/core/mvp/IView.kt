package com.simon.basic.core.mvp

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */
interface IView<P : IPresenter> {
    fun providerPresenter(): P
}