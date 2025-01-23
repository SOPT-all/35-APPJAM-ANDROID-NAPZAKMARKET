package com.napzak.market.data.explore.dto.response

import com.napzak.market.data.explore.dto.ProductItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSellItemsResponse(
    @SerialName("productSellList")
    val productSellList: List<ProductItem>,
)
