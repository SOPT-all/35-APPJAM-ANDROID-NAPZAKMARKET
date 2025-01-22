package com.napzak.market.data.marketinfo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketProductSellItemsResponse(
    @SerialName("productSellList")
    val productSellList: List<ProductSellItem>,
) {
    @Serializable
    data class ProductSellItem(
        @SerialName("productId")
        val productId: Long,
        @SerialName("genreName")
        val genreName: String,
        @SerialName("productName")
        val productName: String,
        @SerialName("photo")
        val photo: String,
        @SerialName("price")
        val price: Int,
        @SerialName("uploadTime")
        val uploadTime: String,
        @SerialName("isInterested")
        val isInterested: Boolean,
        @SerialName("tradeType")
        val tradeType: String,
        @SerialName("tradeStatus")
        val tradeStatus: String,
        @SerialName("isOwnedByCurrentUser")
        val isOwnedByCurrentUser: Boolean,
    )
}
