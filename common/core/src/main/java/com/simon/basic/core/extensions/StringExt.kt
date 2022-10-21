package com.simon.basic.core.extensions

/**
 * @author Simon
 * @date 2020/4/12
 * @desc String Extensions
 */

fun String.Companion.empty() = ""

private val regex = object {
    val phoneish = "^\\s*tel:\\S?\\d+\\S*\\s*$".toRegex(RegexOption.IGNORE_CASE)
    val emailish =
        "^\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}".toRegex(RegexOption.IGNORE_CASE)
    val geoish = "^\\s*geo:\\S*\\d+\\S*\\s*$".toRegex(RegexOption.IGNORE_CASE)
}

fun String.isPhone() = regex.phoneish.matches(this)

fun String.isEmail() = regex.emailish.matches(this)

fun String.isGeoLocation() = regex.geoish.matches(this)