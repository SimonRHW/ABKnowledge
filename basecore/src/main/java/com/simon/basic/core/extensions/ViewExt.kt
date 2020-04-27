package com.simon.basic.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * @author Simon
 * @date 2020/4/12
 * @desc View Extensions
 */
fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.GONE }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

var viewClickFlag = false

var viewRunnable = Runnable { viewClickFlag = false }
fun View.click(action: (view: View) -> Unit) {
    setOnClickListener {
        if (!viewClickFlag) {
            viewClickFlag = true
            action(it)
        }
        removeCallbacks(viewRunnable)
        postDelayed(viewRunnable, 350)
    }
}