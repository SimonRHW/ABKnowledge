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
@Suppress("UNCHECKED_CAST")
abstract class PresenterFragment<P : IPresenter> : BaseFragment(), IView,
    IDelegate {

    lateinit var presenter: P

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(layoutId(), container, false)
        presenter = createPresenter() as P
        presenter.attachView = this
        initWidget()
        return rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}