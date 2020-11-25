package com.simon.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.simon.module.manager.ModuleRouteManager

@Route(path = ModuleRouteManager.ANIMATION_HOME_ROUTE)
class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
    }
}