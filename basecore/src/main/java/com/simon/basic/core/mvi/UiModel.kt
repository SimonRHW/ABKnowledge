package com.simon.basic.core.mvi


/**
 * @author Simon
 * @date 2020/4/12
 * @desc
 */
interface UiModel<State : UiState, Event : UiEvent, Effect : UiEffect> {

    /**
     * 初始化状态
     */
    fun createInitialState(): State

    /**
     * 分发事件
     */
    fun handleEvent(event: Event)

}