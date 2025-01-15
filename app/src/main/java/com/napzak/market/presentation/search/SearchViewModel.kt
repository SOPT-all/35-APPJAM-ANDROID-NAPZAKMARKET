package com.napzak.market.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.search.state.RelatedGenreList
import com.napzak.market.presentation.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {
    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun getGenreList(searchTerm: String) {
        /* TODO: 연관검색어 조회 API 연결 */
        val genreList = if (searchTerm.isEmpty()) emptyList<Genre>() else emptyList()

        updateLoadState(
            loadState = UiState.Success(
                RelatedGenreList(
                    genreList = genreList
                )
            )
        )
    }

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
        debounceSearch()
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    @OptIn(FlowPreview::class)
    private suspend fun debounceSearch() = _searchTerm.debounce(DEBOUNCE_DELAY)
        .collectLatest { debounced ->
            getGenreList(debounced)
        }


    private fun updateLoadState(loadState: UiState<RelatedGenreList>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }

    companion object {
        private const val DEBOUNCE_DELAY = 500L
    }
}