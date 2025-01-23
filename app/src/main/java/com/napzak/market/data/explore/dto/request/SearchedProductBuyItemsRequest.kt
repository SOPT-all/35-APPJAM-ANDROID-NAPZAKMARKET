package com.napzak.market.data.explore.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedProductBuyItemsRequest(
    @SerialName("searchWord")
    val searchWord: String,
    @SerialName("sortOption")
    val sortOption: String,
    @SerialName("genreId")
    val genreIds: List<Long>?,
    @SerialName("isOnSale")
    val isOnSale: Boolean,
    @SerialName("nextCursor")
    val nextCursor: String = "",
)
