package com.simon.basic.core.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simon.basic.core.platform.BaseFragment
import com.simon.basic.core.platform.IDelegate

/**
 * @author Simon
 * @date 2020/5/1
 * @desc
 */

abstract class PresenterFragment<P : IPresenter> : BaseFragment(), IView<P>,
    IDelegate {

    lateinit var presenter: P

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(layoutId(), container, false)
        presenter = providerPresenter()
        presenter.attachView = this
        initWidget()
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}