package com.simon.basic.knowledge.features.contents

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simon.basic.knowledge.R

class ContestsFragment : Fragment() {

    companion object {
        fun newInstance() = ContestsFragment()
    }

    private lateinit var viewModel: ContestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contests_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContestsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
