package com.napzak.market.data.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeRecommendProductResponse(
    @SerialName("productRecommendList")
    val productRecommendList: List<ProductBuyItem>,
)