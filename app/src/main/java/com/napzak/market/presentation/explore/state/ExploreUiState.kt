package com.napzak.market.presentation.explore.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.presentation.explore.type.TradeType

data class ExploreUiState(
    val loadState: UiState<ExploreProductInformation> = UiState.Loading,
    val tradeType: String = TradeType.SELL.name,
    val genreList: List<String> = emptyList(),
    val isOnSale: Boolean = false,
    val isUnopened: Boolean = false,
    val sortType: String = SortType.RECENT.name,
)

data class ExploreProductInformation(
    val productList: List<ProductItem>
)