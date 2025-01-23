package com.napzak.market.data.registration.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductIdDto(
    @SerialName("productId") val productId: Long,
)
