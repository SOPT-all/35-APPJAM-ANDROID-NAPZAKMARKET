package com.napzak.market.data.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeBuyProductResponse(
    @SerialName("productBuyList")
    val productBuyList: List<ProductBuyItem>,
    @SerialName("nextCursor")
    val nextCursor: Boolean?,
)