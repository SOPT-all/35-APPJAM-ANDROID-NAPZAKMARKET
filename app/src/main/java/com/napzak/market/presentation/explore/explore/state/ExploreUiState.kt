package com.napzak.market.presentation.explore.explore.state

import androidx.compose.runtime.Immutable
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.explore.model.Product
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.explore.type.ExploreScreenType

@Immutable
data class ExploreUiState(
    val loadState: UiState<ExploreProductInformation> = UiState.Loading,
    val exploreScreenType: ExploreScreenType = ExploreScreenType.BASIC,
    val initSearchTerm: String? = null,
    val tradeType: TradeType = TradeType.SELL,
    val selectedGenreList: List<Genre> = emptyList(),
    val genreItems: UiState<List<Genre>> = UiState.Loading,
    val isOnSale: Boolean = false,
    val isUnopened: Boolean = false,
    val sortType: SortType = SortType.RECENT,
)

data class ExploreProductInformation(
    val productList: List<Product>,
)