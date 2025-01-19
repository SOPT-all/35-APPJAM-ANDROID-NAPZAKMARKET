package com.napzak.market.presentation.explore.search.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre

data class SearchUiState(
    val loadState: UiState<RelatedGenreList> = UiState.Loading,
    val searchTerm: String = "",
)

data class RelatedGenreList(
    val genreList: List<Genre>,
)