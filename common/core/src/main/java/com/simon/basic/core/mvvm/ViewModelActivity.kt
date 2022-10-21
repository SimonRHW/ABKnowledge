package com.simon.basic.core.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.simon.basic.core.platform.IDelegate
import com.simon.basic.core.platform.ViewBindingActivity

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class ViewModelActivity<V : ViewDataBinding, VM : BaseViewModel> :
    ViewBindingActivity<V>(),
    IDelegate {

    /**
     * 提供view 所使用的ViewModel
     */
    abstract fun obtainViewModel(): VM

    /**
     * 进行数据帮绑定
     */
    abstract fun bind(bind: V, viewModel: VM)
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = obtainViewModel()
        bind(mBinding, mViewModel)
        processViewState()
    }
}