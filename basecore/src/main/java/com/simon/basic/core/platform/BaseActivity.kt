package com.simon.basic.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    abstract fun layoutId(): Int
    abstract fun obtainViewModel(): VM
    abstract fun bind(bind: V, viewModel: VM)
    lateinit var mBinding: V
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mViewModel = obtainViewModel()
        mBinding.lifecycleOwner = this
        bind(mBinding, mViewModel)
    }
}