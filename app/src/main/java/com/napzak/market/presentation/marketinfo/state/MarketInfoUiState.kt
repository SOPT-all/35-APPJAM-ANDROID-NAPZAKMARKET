package com.napzak.market.presentation.marketinfo.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.genre.model.Genre

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
    val storeId: Long,
    val storeNickname: String,
    val storeDescription: String,
    val storePhoto: String,
    val storeBackgroundPhoto: String,
    val genrePreferenceList: List<Genre>,
    val productList: List<ProductItem> = emptyList(),
)
