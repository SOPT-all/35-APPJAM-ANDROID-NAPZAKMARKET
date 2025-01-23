package com.napzak.market.domain.chat.model

data class ChatInfo(
    val nickname: String,
    val firstPhoto: String,
    val tradeType: String,
    val title: String,
    val price: Int,
    val isPriceNegotiable: Boolean,
)
