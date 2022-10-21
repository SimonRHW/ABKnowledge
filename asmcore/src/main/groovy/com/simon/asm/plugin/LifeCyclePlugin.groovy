package com.simon.asm.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LifeCyclePlugin implements Plugin<Project> {
    void apply(Project project) {
        System.out.println("==LifeCyclePlugin gradle plugin==")
        def android = project.extensions.getByType(AppExtension)
        println '----------- registering LifeCyclePluginTransform  -----------'
        LifeCycleTransform transform = new LifeCycleTransform()
        android.registerTransform(transform)
    }
}