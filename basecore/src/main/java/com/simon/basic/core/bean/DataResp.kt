package com.simon.basic.core.bean

import com.google.gson.annotations.SerializedName
import com.simon.basic.core.helper.DataConverter

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */

class DataResp<T : Any?> : MsgResp(), DataConverter<T> {
    @SerializedName(value = "data", alternate = ["result"])
    private var data: T? = null
    override fun convert(): T? {
        return data
    }
}