package com.napzak.market.domain.home.model

data class ProductItem(
    val productId: Long,
    val genreName: String,
    val productName: String,
    val photo: String,
    val price: Int,
    val uploadTime: String,
    val isInterested: Boolean,
    val tradeType: String,
    val tradeStatus: String,
    val isOwnedByCurrentUser: Boolean,
    val isPriceNegotiable: Boolean = false, /* 팔아요 item에서만 사용 */
)