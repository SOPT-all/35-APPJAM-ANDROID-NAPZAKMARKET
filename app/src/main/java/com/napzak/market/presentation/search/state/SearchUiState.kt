package com.napzak.market.presentation.search.state

import com.napzak.market.core.common.state.UiState

data class SearchUiState(
    val loadState: UiState<RelatedSearchTermList> = UiState.Loading,
    val searchTerm: String = "",
)

data class RelatedSearchTermList(
    val genreList: List<String> //리스트로 변경
)