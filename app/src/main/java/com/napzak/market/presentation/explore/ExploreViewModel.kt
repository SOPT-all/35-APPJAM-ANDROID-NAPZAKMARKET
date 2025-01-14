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
        /* TODO: 리스트 조회 API 연결 */
        updateLoadState(
            loadState = UiState.Success(
                ExploreProductInformation(
                    productList = listOf(
                        ProductItem(
                            productId = 201,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 202,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 203,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 204,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "",
                            price = 120000,
                            uploadTime = "3일",
                            isLiked = true,
                            tradeType = "SELL",
                            tradeStatus = "BEFORE_TRADE",
                        ),
                        ProductItem(
                            productId = 205,
                            genreName = "짱구",
                            productName = "피규어",
                            photo = "",
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

    fun changeTradeType(newTradeType: String) {
        _uiState.update { currentState ->
            currentState.copy(
                tradeType = newTradeType
            )
        }
        getExploreProductInformation()
    }

    fun changeSoldOut() {
        _uiState.update { currentState ->
            currentState.copy(
                isOnSale = !uiState.value.isOnSale
            )
        }
        getExploreProductInformation()
    }

    fun changeUnopen() {
        _uiState.update { currentState ->
            currentState.copy(
                isUnopened = !uiState.value.isUnopened
            )
        }
        getExploreProductInformation()
    }

    fun changeItemLikeButton(productId: Int) {
        /* TODO: 좋아요 API 연결 및 기능 연결 */
    }

    private fun updateLoadState(loadState: UiState<ExploreProductInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }
}