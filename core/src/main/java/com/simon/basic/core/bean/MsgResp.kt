package com.simon.basic.core.bean

import com.google.gson.annotations.SerializedName

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */

open class MsgResp : CodeResp() {
    @SerializedName(value = "message", alternate = ["msg", "reason"])
    var message: String? = null
}