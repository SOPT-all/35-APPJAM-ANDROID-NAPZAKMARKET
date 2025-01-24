package com.napzak.market.domain.product.model

data class ProductListFilter(
    val storeId: Long? = null,
    val sortOption: String,
    val genreId: List<Long>?,
    val isOnSale: Boolean,
    val isUnopened: Boolean,
)
