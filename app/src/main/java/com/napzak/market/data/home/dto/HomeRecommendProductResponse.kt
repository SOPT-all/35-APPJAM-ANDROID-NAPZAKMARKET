package com.napzak.market.data.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeRecommendProductResponse(
    @SerialName("productBuyList")
    val productBuyList: List<ProductBuyItem>,
    @SerialName("productSellList")
    val productSellList: List<ProductSellItem>
)