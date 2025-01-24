package com.napzak.market.data.product.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSellItemsRequest(
    @SerialName("sortOption")
    val sortOption: String,
    @SerialName("genreId")
    val genreIds: List<Long>?,
    @SerialName("isOnSale")
    val isOnSale: Boolean,
    @SerialName("isUnopened")
    val isUnopened: Boolean,
    @SerialName("nextCursor")
    val nextCursor: String = "",
)
