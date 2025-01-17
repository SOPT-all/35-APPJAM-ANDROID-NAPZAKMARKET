package com.napzak.market.presentation.mymarket.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.presentation.explore.type.TradeType

data class MarketInfoUiState(
    val loadState: UiState<MarketUiInformation> = UiState.Loading,
    val tradeType: TradeType = TradeType.SELL,
    val initGenreList: List<Genre> = emptyList(),
    val selectedGenreList: List<Genre> = emptyList(),
    val genreList: List<Genre> = emptyList(),
    val isOnSale: Boolean = false,
    val isUnopened: Boolean = false,
    val sortType: SortType = SortType.RECENT,
)

data class MarketUiInformation(
    val productList: List<ProductItem>,
)
