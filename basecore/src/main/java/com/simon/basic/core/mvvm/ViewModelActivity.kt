package com.simon.basic.core.mvvm

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.simon.basic.core.platform.BaseActivity
import com.simon.basic.core.platform.IDelegate

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class ViewModelActivity<V : ViewDataBinding, VM : BaseViewModel> : BaseActivity(),
    IDelegate {

    /**
     * 提供view 所使用的ViewModel
     */
    abstract fun obtainViewModel(): VM

    /**
     * 进行数据帮绑定
     */
    abstract fun bind(bind: V, viewModel: VM)
    lateinit var mBinding: V
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mViewModel = obtainViewModel()
        mBinding.lifecycleOwner = this
        bind(mBinding, mViewModel)
        initWidget()
    }
}