package com.simon.basic.core

/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */


fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
    if (value1 != null && value2 != null) {
        bothNotNull(value1, value2)
    }
}
