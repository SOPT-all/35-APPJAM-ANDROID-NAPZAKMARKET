package com.napzak.market.presentation.detailpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.detailpage.repository.DetailPageRepository
import com.napzak.market.domain.interest.repository.InterestRepository
import com.napzak.market.presentation.detailpage.navigation.DetailPage
import com.napzak.market.presentation.detailpage.state.DetailPageUiState
import com.napzak.market.presentation.detailpage.state.MarketInfoUiState
import com.napzak.market.presentation.detailpage.state.MarketReviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailPageRepository: DetailPageRepository,
    private val interestRepository: InterestRepository,
) : ViewModel() {
    private val productId = savedStateHandle.toRoute<DetailPage>().productId // productId를 가져옴

    private val _uiState = MutableStateFlow(DetailPageUiState())
    val uiState: StateFlow<DetailPageUiState> = _uiState

    private val _sideEffect = MutableSharedFlow<DetailPageSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun loadDetailPageData() {
        viewModelScope.launch {
            detailPageRepository.getProductDetail(productId)
                .onSuccess { productDetail ->
                    _uiState.value = DetailPageUiState(
                        profileImageUrl = productDetail.store.storePhoto,
                        productId = productDetail.detail.productId,
                        tradeType = TradeType.fromName(productDetail.detail.tradeType).name, // 변수 없이 직접 사용
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
                        isInterest = productDetail.isInterested,
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

    fun setProductInterest() {
        if (_uiState.value.isInterest) {
            deleteInterest(productId)
        } else {
            postInterest(productId)
        }
    }

    private fun postInterest(productId: Long) = viewModelScope.launch {
        interestRepository.postInterest(productId)
            .onSuccess {
                _sideEffect.emit(DetailPageSideEffect.ShowLikeSnackBar)
                loadDetailPageData()
            }
    }

    private fun deleteInterest(productId: Long) = viewModelScope.launch {
        interestRepository.deleteInterest(productId)
            .onSuccess {
                loadDetailPageData()
            }
    }

}
