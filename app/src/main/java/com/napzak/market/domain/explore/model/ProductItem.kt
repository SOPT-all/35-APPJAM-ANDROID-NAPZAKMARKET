package com.napzak.market.domain.explore.model

data class ProductItem(
    val productId: Int,
    val genreName: String,
    val productName: String,
    val photo: String,
    val price: Int,
    val uploadTime: String,
    val isLiked: Boolean,
    val tradeType: String,
    val tradeStatus: String,
    val isPriceNegotiable: Boolean = false, /* 팔아요 item에서만 사용 */
)