package com.napzak.market.presentation.detailpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    //private val repository: DetailPageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailPageUiState())
    val uiState: StateFlow<DetailPageUiState> = _uiState

    init {
        loadDetailPageData()
    }

    private fun loadDetailPageData() {
        viewModelScope.launch {
            _uiState.value = DetailPageUiState(
                profileImageUrl = "https://example.com/profile_image.png",
                productId = 1L,
                tradeType = "팔아요",
                genreName = "산리오",
                productName = "딸기 마이멜로디 마스코트 인형",
                price = 50000,
                uploadTime = "3시간 전",
                viewCount = 120,
                interestCount = 45,
                description = "사용한 마이멜로디 판매합니다.",
                productCondition = "미개봉",
                standardDeliveryFee = 3000,
                halfDeliveryFee = 1500,
                isPriceNegotiable = true,
                tradeStatus = "거래전",
                productPhotoUrls = listOf(
                    "https://example.com/photo1.png",
                    "https://example.com/photo2.png",
                ),
                marketInfo = MarketInfoUiState(
                    userId = 101L,
                    nickname = "납작한 외계인",
                    totalProducts = 10,
                    totalTransactions = 7,
                ),
                marketReviews = listOf(
                    MarketReviewUiState(
                        reviewId = 1L,
                        reviewerNickname = "납작한 모르가나",
                        rating = 4.5f,
                        comment = "빠르고 친절하게 거래 완료!",
                        relatedProductId = 1L,
                        relatedProductName = "딸기 마이멜로디 마스코트 인형",
                    )
                )
            )
        }
    }
}
