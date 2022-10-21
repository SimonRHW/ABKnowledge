package com.simon.basic.core.functional

/**
 * @author Simon
 * @date 2020/4/12
 * @desc 错误状态
 */
sealed class Failure {

    object NetworkConnection : Failure()

    object ServerError : Failure()

    /**
     * 针对特定异常拓展此类。
     */
    abstract class FeatureFailure : Failure()
}