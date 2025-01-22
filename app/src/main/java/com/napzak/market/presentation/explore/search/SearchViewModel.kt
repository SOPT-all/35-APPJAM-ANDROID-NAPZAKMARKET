package com.napzak.market.presentation.explore.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.respository.GenreRepository
import com.napzak.market.presentation.explore.search.state.RelatedGenreList
import com.napzak.market.presentation.explore.search.state.SearchUiState
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
    private val genreRepository: GenreRepository,
) : ViewModel() {
    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
        debounceSearch()
    }

    fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    @OptIn(FlowPreview::class)
    fun debounceSearch() = viewModelScope.launch {
        _searchTerm.debounce(DEBOUNCE_DELAY)
            .collectLatest { debounced ->
                getGenreList(debounced)
            }
    }

    fun getGenreList(searchTerm: String) = viewModelScope.launch {
        if (searchTerm.isBlank()) {
            updateLoadState(
                UiState.Success(
                    RelatedGenreList(genreList = emptyList())
                )
            )
        } else {
            genreRepository.fetchSearchResultGenreItems(searchTerm)
                .onSuccess { response ->
                    updateLoadState(
                        UiState.Success(
                            RelatedGenreList(genreList = response)
                        )
                    )
                }
                .onFailure {
                    updateLoadState(
                        UiState.Failure(
                            it.message.toString()
                        )
                    )
                }
        }
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