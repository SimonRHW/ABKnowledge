package com.simon.basic.core.mvp

import android.os.Bundle
import com.simon.basic.core.platform.BaseActivity
import com.simon.basic.core.platform.IDelegate

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */

@Suppress("UNCHECKED_CAST")
abstract class PresenterActivity<P : IPresenter> : BaseActivity(), IView,
    IDelegate {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter = providerPresenter() as P
        presenter.attachView = this
        initWidget()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}