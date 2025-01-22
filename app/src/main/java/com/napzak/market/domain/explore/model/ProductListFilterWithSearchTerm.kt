package com.napzak.market.domain.explore.model

data class ProductListFilterWithSearchTerm(
    val searchWord: String,
    val sortOption: String,
    val genreId: List<Long>?,
    val isOnSale: Boolean,
    val isUnopened: Boolean,
)
