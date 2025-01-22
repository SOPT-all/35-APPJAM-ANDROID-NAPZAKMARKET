package com.napzak.market.presentation.detailpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.detailpage.repository.DetailPageRepository
import com.napzak.market.presentation.detailpage.state.DetailPageUiState
import com.napzak.market.presentation.detailpage.state.MarketInfoUiState
import com.napzak.market.presentation.detailpage.state.MarketReviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val repository: DetailPageRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailPageUiState())
    val uiState: StateFlow<DetailPageUiState> = _uiState


    init {
        val productId = savedStateHandle.get<Long>("productId")
            ?: throw IllegalArgumentException("Product ID is missing or invalid")
        loadDetailPageData(productId)
    }

    private fun loadDetailPageData(productId: Long) {
        viewModelScope.launch {
            repository.getProductDetail(productId)
                .onSuccess { productDetail ->
                    val tradeType = TradeType.fromName(productDetail.detail.tradeType)
                    _uiState.value = DetailPageUiState(
                        profileImageUrl = productDetail.store.storePhoto,
                        productId = productDetail.detail.productId,
                        tradeType = tradeType.name,
                        genreName = productDetail.detail.genreName,
                        productName = productDetail.detail.productName,
                        price = productDetail.detail.price,
                        uploadTime = productDetail.detail.uploadTime,
                        viewCount = productDetail.detail.viewCount,
                        interestCount = productDetail.detail.interestCount,
                        description = productDetail.detail.description,
                        productCondition = productDetail.detail.productCondition,
                        standardDeliveryFee = productDetail.detail.standardDeliveryFee,
                        halfDeliveryFee = productDetail.detail.halfDeliveryFee,
                        isPriceNegotiable = productDetail.detail.isPriceNegotiable,
                        tradeStatus = productDetail.detail.tradeStatus,
                        productPhotoUrls = productDetail.photos.map { it.photoUrl },
                        marketInfo = MarketInfoUiState(
                            userId = productDetail.store.userId,
                            nickname = productDetail.store.nickname,
                            totalProducts = productDetail.store.totalProducts,
                            totalTransactions = productDetail.store.totalTransactions,
                        ),
                        marketReviews = productDetail.reviews.map {
                            MarketReviewUiState(
                                reviewId = it.reviewId,
                                reviewerNickname = it.reviewerNickname,
                                rating = it.rating,
                                comment = it.comment,
                                relatedProductId = it.relatedProductId,
                                relatedProductName = it.relatedProductName,
                            )
                        }
                    )
                }
                .onFailure { throwable ->
                    throwable.printStackTrace()
                }
        }
    }
}
