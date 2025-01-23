package com.napzak.market.presentation.detailpage.state

import androidx.compose.runtime.Immutable

@Immutable
data class DetailPageUiState(
    val profileImageUrl: String? = null,
    val productId: Long = 0L,
    val tradeType: String = "",
    val genreName: String = "",
    val productName: String = "",
    val price: Int = 0,
    val uploadTime: String = "",
    val viewCount: Int = 0,
    val interestCount: Int = 0,
    val description: String = "",
    val productCondition: String = "",
    val standardDeliveryFee: Int = 0,
    val halfDeliveryFee: Int = 0,
    val isInterest: Boolean = false,
    val isPriceNegotiable: Boolean = false,
    val isOwnedByCurrentUser: Boolean = true,
    val tradeStatus: String = "",
    val productPhotoUrls: List<String> = emptyList(),
    val marketInfo: MarketInfoUiState = MarketInfoUiState(),
    val marketReviews: List<MarketReviewUiState> = emptyList(),
)

@Immutable
data class MarketInfoUiState(
    val userId: Long = 0L,
    val nickname: String = "",
    val totalProducts: Int = 0,
    val totalTransactions: Int = 0,
)

@Immutable
data class MarketReviewUiState(
    val reviewId: Long = 0L,
    val reviewerNickname: String = "",
    val rating: Float = 0.0f,
    val comment: String = "",
    val relatedProductId: Long = 0L,
    val relatedProductName: String = "",
)
