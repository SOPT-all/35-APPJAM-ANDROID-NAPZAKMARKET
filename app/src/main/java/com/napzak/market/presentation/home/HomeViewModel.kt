package com.napzak.market.presentation.home

import androidx.lifecycle.ViewModel
import com.napzak.market.presentation.home.state.HomeUiState
import com.napzak.market.presentation.home.state.HomeUiState.Companion.dummyBanner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getBannerImages() = _uiState.update { currentState ->
        currentState.copy(
            bannerImages = dummyBanner
        )
    }

    fun getRecommendedItems() = _uiState.update { currentState ->
        currentState.copy(
            recommendedItems = HomeUiState.dummyData
        )
    }

    fun getPopularItems() = _uiState.update { currentState ->
        currentState.copy(
            popularItems = HomeUiState.dummyData
        )
    }

    fun getMostSearchedItems() = _uiState.update { currentState ->
        currentState.copy(
            searchItems = HomeUiState.dummyData
        )
    }
}