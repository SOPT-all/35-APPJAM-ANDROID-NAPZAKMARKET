package com.napzak.market.data.product.dto.response

import com.napzak.market.data.product.dto.ProductItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductRecommendItemResponse(
    @SerialName("productRecommendList")
    val productRecommendList: List<ProductItem>
)