package com.napzak.market.data.chat.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatInfoResponse(
    @SerialName("nickname") val nickname: String,
    @SerialName("firstPhoto") val firstPhoto: String,
    @SerialName("tradeType") val tradeType: String,
    @SerialName("title") val title: String,
    @SerialName("price") val price: Int,
    @SerialName("isPriceNegotiable") val isPriceNegotiable: Boolean,
)
