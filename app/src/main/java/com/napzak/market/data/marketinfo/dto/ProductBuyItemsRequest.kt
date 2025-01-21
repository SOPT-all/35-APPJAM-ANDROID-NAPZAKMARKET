package com.napzak.market.data.marketinfo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductBuyItemsRequest(
    @SerialName("storeOwnerId")
    val storeOwnerId: Long,
    @SerialName("sortOption")
    val sortOption: String,
    @SerialName("genreId")
    val genreIds: List<Long>?,
    @SerialName("isOnSale")
    val isOnSale: Boolean,
    @SerialName("nextCursor")
    val nextCursor: String = "",
)
