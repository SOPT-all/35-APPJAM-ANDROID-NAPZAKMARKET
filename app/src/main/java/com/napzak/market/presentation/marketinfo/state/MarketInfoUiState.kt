package com.napzak.market.presentation.marketinfo.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.MarketTab
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.genre.model.Genre

data class MarketInfoUiState(
    val loadMarketInfoState: UiState<MarketUiInformation> = UiState.Loading,
    val loadProductItemsState: UiState<MarketProductItemsInformation> = UiState.Loading,
    val storeId: Long = 0,
    val marketTab: MarketTab = MarketTab.SELL,
    val initGenreList: List<Genre> = emptyList(),
    val selectedGenreList: List<Genre> = emptyList(),
    val genreList: List<Genre> = emptyList(),
    val isOnSale: Boolean = false,
    val isUnopened: Boolean = false,
    val sortType: SortType = SortType.RECENT,
)

data class MarketUiInformation(
    val storeNickname: String,
    val storeDescription: String,
    val storePhoto: String,
    val storeCover: String,
    val genrePreferenceList: List<Genre>,
)

data class MarketProductItemsInformation(
    val productList: List<ProductItem> = emptyList(),
)
