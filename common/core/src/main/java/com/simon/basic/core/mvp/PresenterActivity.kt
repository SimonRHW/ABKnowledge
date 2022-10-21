package com.simon.basic.core.mvp

import android.os.Bundle
import com.simon.basic.core.platform.BaseActivity
import com.simon.basic.core.platform.IDelegate

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */

abstract class PresenterActivity<P : IPresenter> : BaseActivity(), IView<P>, IDelegate {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter = providerPresenter()
        presenter.attachView = this
        processViewState()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}