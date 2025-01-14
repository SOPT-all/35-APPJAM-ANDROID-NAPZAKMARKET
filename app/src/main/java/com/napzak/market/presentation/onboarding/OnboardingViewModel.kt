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

    @OptIn(FlowPreview::class)
    fun changeSearchText(newValue: String) = viewModelScope.launch {
        with(_searchValue) {
            update { newValue }
            debounce(500L)
                .collectLatest { debounced ->
                    getGenreList(debounced)
                }
        }
    }

    private fun getGenreList(searchTerm: String) {
        _uiState.update { currentState ->
            currentState.copy(
                genreList = UiState.Success(if (searchTerm.isEmpty()) {
                    OnboardingUiState.initialGenreList.data
                } else {
                    OnboardingUiState.initialGenreList.data.filter {
                        it.genreName.contains(
                            searchTerm
                        )
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
                selectedGenreList = if (_uiState.value.selectedGenreList.isEmpty()) {
                    listOf(genre)
                } else {
                    currentState.selectedGenreList.toMutableList() + genre
                }
            )
        }
    }

    companion object {
        private const val MAX_CHOICE = 4
    }
}