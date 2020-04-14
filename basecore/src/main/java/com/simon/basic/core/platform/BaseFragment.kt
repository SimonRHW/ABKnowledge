package com.simon.basic.core.platform

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
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var mBinding: V
    lateinit var mViewModel: VM
    abstract fun layoutId(): Int
    abstract fun obtainViewModel(): VM
    abstract fun bind(bind: V, viewModel: VM)
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
        mViewModel = obtainViewModel()
        mBinding.lifecycleOwner = this
        bind(mBinding, mViewModel)
        return mBinding.root
    }
}