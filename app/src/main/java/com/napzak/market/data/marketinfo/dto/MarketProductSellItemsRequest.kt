package com.napzak.market.data.marketinfo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketProductSellItemsRequest(
    @SerialName("storeOwnerId")
    val storeId: Long?,
    @SerialName("sortOption")
    val sortOption: String,
    @SerialName("genreId")
    val genreIds: List<Long>?,
    @SerialName("isOnSale")
    val isOnSale: Boolean,
    @SerialName("isUnopened")
    val isUnopened: Boolean,
)
