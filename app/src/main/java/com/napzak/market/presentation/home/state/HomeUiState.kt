package com.napzak.market.presentation.home.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.banner.model.HomeBanner
import com.napzak.market.domain.product.model.Product

data class HomeUiState(
    val isLoaded: UiState<Unit> = UiState.Loading,
    val bannerImages: UiState<List<HomeBanner>> = UiState.Loading,
    val recommendedItems: UiState<List<Product>> = UiState.Loading,
    val popularItems: UiState<List<Product>> = UiState.Loading,
    val searchItems: UiState<List<Product>> = UiState.Loading,
)
