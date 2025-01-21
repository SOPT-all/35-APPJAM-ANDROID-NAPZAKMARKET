package com.napzak.market.domain.marketinfo.model

data class ProductListFilter(
    val sortOption: String,
    val genreId: List<Long>?,
    val isOnSale: Boolean,
    val isUnopened: Boolean,
)
