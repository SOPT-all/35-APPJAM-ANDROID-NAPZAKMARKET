package com.napzak.market.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.onboarding.state.OnboardingUiState
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
class OnboardingViewModel @Inject constructor(

) : ViewModel() {
    private val _searchValue: MutableStateFlow<String> = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _uiState: MutableStateFlow<OnboardingUiState> =
        MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
        debounceSearch()
    }

    private fun updateSearchValue(newValue: String) = _searchValue.update { newValue }

    @OptIn(FlowPreview::class)
    private suspend fun debounceSearch() = _searchValue.debounce(DEBOUNCE_DELAY)
        .collectLatest { debounced ->
            getGenreList(debounced)
        }

    // TODO: 서버 통신으로 대체
    private fun getGenreList(searchTerm: String) {
        _uiState.update { currentState ->
            currentState.copy(
                genreList = UiState.Success(
                    if (searchTerm.isEmpty()) {
                        OnboardingUiState.initialGenreList.data
                    } else {
                        OnboardingUiState.initialGenreList.data.filter {
                            it.genreName.contains(searchTerm)
                        }
                    }
                )
            )
        }
    }

    fun selectGenre(genre: Genre) {
        if (_uiState.value.selectedGenreList.map { it.genreId }.contains(genre.genreId)) {
            removeSelectedGenre(genre)
        } else if (_uiState.value.selectedGenreList.size < MAX_CHOICE) {
            addSelectedGenre(genre)
        }
    }

    fun clearSelectedGenre() = _uiState.update { currentState ->
        currentState.copy(
            selectedGenreList = emptyList()
        )
    }

    private fun removeSelectedGenre(genre: Genre) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenreList = currentState.selectedGenreList.filter { it.genreId != genre.genreId }
            )
        }
    }

    private fun addSelectedGenre(genre: Genre) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenreList = currentState.selectedGenreList + genre

            )
        }
    }

    companion object {
        private const val MAX_CHOICE = 4
        private const val DEBOUNCE_DELAY = 500L
    }
}