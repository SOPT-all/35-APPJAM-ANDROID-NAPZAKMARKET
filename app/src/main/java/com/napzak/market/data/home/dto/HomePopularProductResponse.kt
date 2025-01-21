package com.napzak.market.data.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomePopularProductResponse(
    @SerialName("productSellList")
    val productSellList: List<ProductSellItem>,
    @SerialName("nextCursor")
    val nextCursor: Boolean?,
)