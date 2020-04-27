package com.simon.basic.core.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.simon.basic.core.platform.BaseFragment
import com.simon.basic.core.platform.IDelegate

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class ViewModelFragment<V : ViewDataBinding, VM : BaseViewModel> : BaseFragment(),
    IDelegate {
    lateinit var mBinding: V
    lateinit var mViewModel: VM
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
        initWidget()
        return mBinding.root
    }
}