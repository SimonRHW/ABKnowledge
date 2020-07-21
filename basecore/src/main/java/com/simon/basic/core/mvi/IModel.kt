package com.simon.basic.core.mvi

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel


/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
interface IModel<S : IState, I : IIntent> {
    val intents: Channel<I>
    val state: LiveData<S>
}