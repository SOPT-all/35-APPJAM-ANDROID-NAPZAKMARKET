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
        val genreList = if (searchTerm.isEmpty()) {
            emptyList<Genre>()
        } else {
            listOf(
                Genre(
                    genreId = 1,
                    genreName = "나루토",
                ),
                Genre(
                    genreId = 2,
                    genreName = "원피스",
                ),
                Genre(
                    genreId = 3,
                    genreName = "블리치",
                ),
                Genre(
                    genreId = 4,
                    genreName = "귀멸의 칼날",
                ),
                Genre(
                    genreId = 5,
                    genreName = "주술회전",
                ),
                Genre(
                    genreId = 6,
                    genreName = "진격의 거인",
                ),
                Genre(
                    genreId = 7,
                    genreName = "데스노트",
                ),
                Genre(
                    genreId = 8,
                    genreName = "짱구는 못말려",
                ),
                Genre(
                    genreId = 9,
                    genreName = "도라에몽",
                ),
                Genre(
                    genreId = 10,
                    genreName = "강철의 연금술사",
                ),
                Genre(
                    genreId = 11,
                    genreName = "체인소맨",
                ),
                Genre(
                    genreId = 12,
                    genreName = "원펀맨",
                ),
                Genre(
                    genreId = 13,
                    genreName = "드래곤볼",
                ),
                Genre(
                    genreId = 14,
                    genreName = "명탐정 코난",
                ),
                Genre(
                    genreId = 15,
                    genreName = "슬램덩크",
                )
            )
        }

        updateLoadState(
            loadState = UiState.Success(
                RelatedGenreList(
                    genreList = genreList.filter {
                        it.genreName.contains(searchTerm)
                    }
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