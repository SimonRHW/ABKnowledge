package com.simon.basic.core.bean

import com.simon.basic.core.DataConverter

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
class DataResp<T : Any?> : MsgResp(), DataConverter<T> {
    private var data: T? = null
    override fun convert(): T? {
        return data
    }
}