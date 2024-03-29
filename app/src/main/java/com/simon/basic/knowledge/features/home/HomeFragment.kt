package com.simon.basic.knowledge.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.simon.basic.knowledge.databinding.HomeFragmentBinding
import com.simon.module.manager.News

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNews.setOnClickListener(View.OnClickListener {
            // 这个页面主动指定了Group名
            ARouter.getInstance().build(News.HOME_ROUTE).navigation()
        })
    }

}
