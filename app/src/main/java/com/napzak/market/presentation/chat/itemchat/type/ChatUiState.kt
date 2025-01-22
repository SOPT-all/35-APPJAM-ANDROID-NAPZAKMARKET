package com.napzak.market.presentation.chat.itemchat.type

data class ChatUiState(
    val nickname: String = "",
    val firstPhoto: String = "",
    val tradeType: String = "",
    val title: String = "",
    val price: Int = 0,
    val isPriceNegotiable: Boolean = false,
)
