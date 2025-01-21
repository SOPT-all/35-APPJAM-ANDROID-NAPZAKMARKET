package com.napzak.market.data.explore.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductBuyListResponse(
    @SerialName("ProductBuyList")
    val productBuyList: List<ProductBuyItem>,
) {
    @Serializable
    data class ProductBuyItem(
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
        @SerialName("isPriceNegotiable")
        val isPriceNegotiable: Boolean,
        @SerialName("isOwnedByCurrentUser")
        val isOwnedByCurrentUser: Boolean,
        @SerialName("nextCursor") // TODO: 서버측에 확인 필요
        val nextCursor: String = "",
    )
}
