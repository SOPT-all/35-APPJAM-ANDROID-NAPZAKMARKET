package com.napzak.market.domain.explore.model

data class ProductItem(
    val productId: Long,
    val genreName: String,
    val productName: String,
    val photo: String,
    val price: Int,
    val uploadTime: String,
    val isLiked: Boolean,
    val tradeType: String,
    val tradeStatus: String,
    val isPriceNegotiable: Boolean = false, /* 구해요 item에서만 사용 */
    val isOwnedByCurrentUser: Boolean = false,/* TODO: 추후 default값 삭제 필요 */
)