package com.napzak.market.presentation.explore

import androidx.lifecycle.ViewModel
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.state.ExploreProductInformation
import com.napzak.market.presentation.explore.state.ExploreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExploreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    fun getExploreProductInformation() {
        TODO("추후 서버 통신 연결")
        updateLoadState(
            loadState = UiState.Success(
                ExploreProductInformation(
                    listOf(
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "http://example.com/photo3.jpg",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "http://example.com/photo3.jpg",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "http://example.com/photo3.jpg",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "http://example.com/photo3.jpg",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "http://example.com/photo3.jpg",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                    )
                )
            )
        )
    }

    private fun updateLoadState(loadState: UiState<ExploreProductInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }
}