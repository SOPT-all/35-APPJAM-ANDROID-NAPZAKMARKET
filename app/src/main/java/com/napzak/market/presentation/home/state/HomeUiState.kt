package com.napzak.market.presentation.home.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem

data class HomeUiState(
    val isLoaded: UiState<Unit> = UiState.Loading,
    val bannerImages: UiState<List<HomeBanner>> = UiState.Loading,
    val recommendedItems: UiState<List<ProductItem>> = UiState.Loading,
    val popularItems: UiState<List<ProductItem>> = UiState.Loading,
    val searchItems: UiState<List<ProductItem>> = UiState.Loading,
)
