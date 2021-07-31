package com.simon.basic.core.platform

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class ViewBindingActivity<V : ViewDataBinding> : BaseActivity() {

    lateinit var mBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = layoutId()
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.lifecycleOwner = this
    }

}