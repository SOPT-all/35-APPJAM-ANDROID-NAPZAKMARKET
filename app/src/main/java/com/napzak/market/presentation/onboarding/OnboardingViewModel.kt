package com.napzak.market.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.onboarding.state.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState: MutableStateFlow<OnboardingUiState> =
        MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    fun changeSearchText(text: String) = _uiState.update { currentState ->
        currentState.copy(searchText = text)
    }

    fun selectGenre(genre: Genre) {
        Timber.tag("FindingError").d("Enters selectGenre")
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

        Timber.tag("FindingError").d("remove selectGenre")
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenreList = currentState.selectedGenreList.filter { it.genreId != genre.genreId }
            )
        }
    }

    private fun addSelectedGenre(genre: Genre) {
        Timber.tag("FindingError").d("add selectGenre")

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