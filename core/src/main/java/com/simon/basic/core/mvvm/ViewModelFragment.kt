package com.simon.basic.core.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.simon.basic.core.platform.IDelegate
import com.simon.basic.core.platform.ViewBindingFragment

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class ViewModelFragment<V : ViewDataBinding, VM : BaseViewModel> :
    ViewBindingFragment<V>(),
    IDelegate {
    lateinit var mViewModel: VM
    abstract fun obtainViewModel(): VM
    abstract fun bind(bind: V, viewModel: VM)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mViewModel = obtainViewModel()
        bind(mBinding, mViewModel)
        initWidget()
        return mBinding.root
    }
}