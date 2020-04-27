package com.simon.basic.core.platform

import androidx.appcompat.app.AppCompatActivity

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun layoutId(): Int
}