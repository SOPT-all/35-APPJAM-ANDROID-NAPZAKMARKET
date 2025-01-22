package com.napzak.market.presentation.onboarding.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre

data class OnboardingUiState(
    val genreList: UiState<List<Genre>> = UiState.Empty,
    val selectedGenreList: List<Genre> = emptyList(),
    val searchText: String = "",
)
