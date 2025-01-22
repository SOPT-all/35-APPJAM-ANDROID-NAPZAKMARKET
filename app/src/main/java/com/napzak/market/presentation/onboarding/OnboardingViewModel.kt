package com.napzak.market.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.usecase.GenreRegisterUseCase
import com.napzak.market.domain.genre.usecase.ImageGenreSearchUseCase
import com.napzak.market.presentation.onboarding.state.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val genreSearchUseCase: ImageGenreSearchUseCase,
    private val genreRegisterUseCase: GenreRegisterUseCase,
) : ViewModel() {

    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    private val _uiState: MutableStateFlow<OnboardingUiState> =
        MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<OnboardingSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    fun debounceSearch() = viewModelScope.launch {
        _searchTerm.debounce(DEBOUNCE_DELAY)
            .collectLatest { debounced ->
                getGenreList(debounced)
            }
    }

    private fun getGenreList(searchTerm: String) = viewModelScope.launch {
        genreSearchUseCase(searchTerm)
            .onSuccess { response ->
                _uiState.update { currentState ->
                    currentState.copy(genres = UiState.Success(response))
                }
            }
            .onFailure {
                _uiState.update { currentState ->
                    currentState.copy(
                        genres = UiState.Failure(
                            it.message.toString()
                        )
                    )
                }
            }
    }

    fun selectGenre(genre: Genre) {
        if (_uiState.value.selectedGenres.map { it.genreId }.contains(genre.genreId)) {
            removeSelectedGenre(genre)
        } else if (_uiState.value.selectedGenres.size < MAX_CHOICE) {
            addSelectedGenre(genre)
        }
    }

    fun clearSelectedGenre() = _uiState.update { currentState ->
        currentState.copy(
            selectedGenres = emptyList(),
        )
    }

    private fun removeSelectedGenre(genre: Genre) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenres = currentState.selectedGenres.filter {
                    it.genreId != genre.genreId
                },
            )
        }
    }

    private fun addSelectedGenre(genre: Genre) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGenres = currentState.selectedGenres + genre

            )
        }
    }

    fun registerInterestGenres() = viewModelScope.launch {
        genreRegisterUseCase(_uiState.value.selectedGenres)
            .onSuccess {
                _sideEffect.emit(OnboardingSideEffect.NavigateToHome)
            }
    }

    fun skipRegisterGenres() = viewModelScope.launch {
        _sideEffect.emit(OnboardingSideEffect.NavigateToHome)
    }

    companion object {
        private const val MAX_CHOICE = 4
        private const val DEBOUNCE_DELAY = 500L
    }
}