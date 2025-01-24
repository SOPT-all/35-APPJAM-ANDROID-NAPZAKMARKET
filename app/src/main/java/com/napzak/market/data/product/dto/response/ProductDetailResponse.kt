package com.napzak.market.data.product.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailResponse(
    @SerialName("isInterested")
    val isInterested: Boolean,
    @SerialName("productDetail")
    val productDetail: ProductDetail,
    @SerialName("productPhotoList")
    val productPhotoItems: List<ProductPhoto>,
    @SerialName("storeInfo")
    val storeInfo: StoreInfo,
    @SerialName("storeReviewList")
    val storeReviewItems: List<StoreReview>,
) {
    @Serializable
    data class ProductDetail(
        @SerialName("productId")
        val productId: Long,
        @SerialName("tradeType")
        val tradeType: String,
        @SerialName("genreName")
        val genreName: String,
        @SerialName("productName")
        val productName: String,
        @SerialName("price")
        val price: Int,
        @SerialName("uploadTime")
        val uploadTime: String,
        @SerialName("viewCount")
        val viewCount: Int,
        @SerialName("interestCount")
        val interestCount: Int,
        @SerialName("description")
        val description: String,
        @SerialName("productCondition")
        val productCondition: String? = null,
        @SerialName("standardDeliveryFee")
        val standardDeliveryFee: Int,
        @SerialName("halfDeliveryFee")
        val halfDeliveryFee: Int,
        @SerialName("isDeliveryIncluded")
        val isDeliveryIncluded: Boolean,
        @SerialName("isPriceNegotiable")
        val isPriceNegotiable: Boolean,
        @SerialName("tradeStatus")
        val tradeStatus: String,
        @SerialName("isOwnedByCurrentUser")
        val isOwnedByCurrentUser: Boolean,
    )

    @Serializable
    data class ProductPhoto(
        @SerialName("photoId")
        val photoId: Long,
        @SerialName("photoUrl")
        val photoUrl: String,
        @SerialName("photoSequence")
        val photoSequence: Int,
    )

    @Serializable
    data class StoreInfo(
        @SerialName("userId")
        val userId: Long,
        @SerialName("storePhoto")
        val storePhoto: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("totalProducts")
        val totalProducts: Int,
        @SerialName("totalTransactions")
        val totalTransactions: Int,
    )

    @Serializable
    data class StoreReview(
        @SerialName("reviewId")
        val reviewId: Long,
        @SerialName("reviewerNickname")
        val reviewerNickname: String,
        @SerialName("rating")
        val rating: Float,
        @SerialName("comment")
        val comment: String,
        @SerialName("relatedProductId")
        val relatedProductId: Long,
        @SerialName("relatedProductName")
        val relatedProductName: String,
    )
}
