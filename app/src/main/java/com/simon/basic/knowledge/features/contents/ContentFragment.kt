package com.simon.basic.knowledge.features.contents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.simon.basic.knowledge.R
import com.simon.module.manager.ModuleRouteManager
import kotlinx.android.synthetic.main.contests_fragment.*

class ContentFragment : Fragment() {

    companion object {
        fun newInstance() = ContentFragment()
    }

    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contests_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        // TODO: Use the ViewModel

        btnAnimation.setOnClickListener(View.OnClickListener {
            // 这个页面主动指定了Group名
            ARouter.getInstance().build(ModuleRouteManager.ANIMATION_HOME_ROUTE).navigation()
        })
    }

}
