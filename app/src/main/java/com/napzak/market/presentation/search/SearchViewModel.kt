package com.napzak.market.presentation.search

import androidx.lifecycle.ViewModel
import com.napzak.market.core.common.state.UiState
import com.napzak.market.presentation.search.state.RelatedSearchTermList
import com.napzak.market.presentation.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun getRelatedSearchTermList() {
        /* TODO: 연관검색어 조회 API 연결 */
        updateLoadState(
            loadState = UiState.Success(
                RelatedSearchTermList(
                    genreList = emptyList()
                )
            )
        )
    }

    fun updateSearchTerm(newSearchTerm: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchTerm = newSearchTerm
            )
        }
    }

    private fun updateLoadState(loadState: UiState<RelatedSearchTermList>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }
}