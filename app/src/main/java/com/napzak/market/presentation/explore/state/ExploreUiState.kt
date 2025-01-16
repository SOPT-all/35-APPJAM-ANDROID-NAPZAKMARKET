package com.napzak.market.presentation.explore.state

import androidx.compose.runtime.Immutable
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.type.ExploreScreenType
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.presentation.explore.type.TradeType

@Immutable
data class ExploreUiState(
    val loadState: UiState<ExploreProductInformation> = UiState.Loading,
    val exploreScreenType: String = ExploreScreenType.BASIC.name,
    val initSearchTerm: String? = null,
    val tradeType: String = TradeType.SELL.name,
    val initGenreList: List<Genre> = emptyList(),
    val selectedGenreList: List<Genre> = emptyList(),
    val genreList: List<Genre> = emptyList(),
    val isOnSale: Boolean = false,
    val isUnopened: Boolean = false,
    val sortType: String = SortType.RECENT.name,
)

data class ExploreProductInformation(
    val productList: List<ProductItem>,
)