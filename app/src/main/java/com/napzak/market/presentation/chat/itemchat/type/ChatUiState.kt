package com.napzak.market.presentation.chat.itemchat.type

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.chat.model.ChatInfo

data class ChatUiState(
    val loadState: UiState<ChatInfo> = UiState.Loading,
)
