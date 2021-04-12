package com.simon.basic.core.extensions

import android.content.Context

/**
 * @author Simon
 * @date 2021/4/11
 * @time 5:20 PM
 * @desc
 */

val Context.appVersionName: String?
    get() {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        return packageInfo.versionName
    }

/**
 * Returns the name (label) of the application or the package name as a fallback.
 */
val Context.appName: String
    get() {
        return packageManager.getApplicationLabel(applicationInfo).toString()
    }