package com.simon.basic.knowledge.features.contents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.simon.basic.knowledge.databinding.ContentsFragmentBinding
import com.simon.module.manager.ModuleRouteManager

class ContentFragment : Fragment() {

    private var _binding: ContentsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContentsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnAnimation.setOnClickListener(View.OnClickListener {
            // 这个页面主动指定了Group名
            ARouter.getInstance().build(ModuleRouteManager.ANIMATION_HOME_ROUTE).navigation()
        })
    }

}
