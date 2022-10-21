package com.simon.basic.core.bean

import com.google.gson.annotations.SerializedName

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
abstract class CodeResp {
    @SerializedName(value = "code", alternate = ["error_code"])
    var code: Int = 0
}