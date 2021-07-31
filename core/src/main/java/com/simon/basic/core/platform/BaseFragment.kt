package com.simon.basic.core.platform

import androidx.fragment.app.Fragment

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class BaseFragment : Fragment() {
    abstract fun layoutId(): Int
}