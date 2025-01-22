package com.napzak.market.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoContentBaseResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
)