package com.simon.basic.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel> : AppCompatActivity() {
    abstract fun layoutId(): Int
    abstract fun obtainViewModel(): M
    lateinit var mBinding: V
    lateinit var mViewModel: M
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this
        mViewModel = obtainViewModel()
    }
}