package com.simon.basic.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class BaseFragment<V : ViewDataBinding, M : BaseViewModel> :Fragment() {
    lateinit var mBinding: V
    lateinit var mViewModel: M
    abstract fun layoutId(): Int
    abstract fun obtainViewModel(): M
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            layoutId(),
            container,
            false
        )
        mBinding.lifecycleOwner = this
        mViewModel = obtainViewModel()
        return mBinding.root
    }
}