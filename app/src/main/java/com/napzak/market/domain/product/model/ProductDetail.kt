package com.napzak.market.domain.product.model

data class ProductDetail(
    val isInterested: Boolean,
    val detail: Detail,
    val photos: List<Photo>,
    val store: Store,
    val reviews: List<Review>,
) {
    data class Detail(
        val productId: Long,
        val tradeType: String,
        val genreName: String,
        val productName: String,
        val price: Int,
        val uploadTime: String,
        val viewCount: Int,
        val interestCount: Int,
        val description: String,
        val productCondition: String,
        val standardDeliveryFee: Int,
        val halfDeliveryFee: Int,
        val isDeliveryIncluded: Boolean,
        val isPriceNegotiable: Boolean,
        val tradeStatus: String,
        val isOwnedByCurrentUser: Boolean,
    )

    data class Photo(
        val photoId: Long,
        val photoUrl: String,
        val photoSequence: Int,
    )

    data class Store(
        val userId: Long,
        val storePhoto: String,
        val nickname: String,
        val totalProducts: Int,
        val totalTransactions: Int,
    )

    data class Review(
        val reviewId: Long,
        val reviewerNickname: String,
        val rating: Float,
        val comment: String,
        val relatedProductId: Long,
        val relatedProductName: String,
    )
}
