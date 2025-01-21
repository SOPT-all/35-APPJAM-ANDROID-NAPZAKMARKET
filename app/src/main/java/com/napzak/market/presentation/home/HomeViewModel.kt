package com.napzak.market.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem
import com.napzak.market.domain.home.repository.HomeRepository
import com.napzak.market.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getBannerImages() = viewModelScope.launch {
        homeRepository.fetchHomeBannerList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    updateBannerList(UiState.Empty)
                } else {
                    updateBannerList(UiState.Success(response))
                }
            }
            .onFailure { response ->
                Timber.e(response.message)
            }
    }

    fun getRecommendedItems() = viewModelScope.launch {
        homeRepository.fetchRecommendProductList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    updateRecommendedProductList(UiState.Empty)
                } else {
                    updateRecommendedProductList(UiState.Success(response))
                }
            }
            .onFailure { response ->
                Timber.e(response.message)
            }
    }

    fun getPopularItems() = viewModelScope.launch {
        homeRepository.fetchPopularProductList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    updatePopularProductList(UiState.Empty)
                } else {
                    updatePopularProductList(UiState.Success(response))
                }
            }
            .onFailure { response ->
                Timber.e(response.message)
            }
    }

    fun getMostSearchedItems() = _uiState.update { currentState ->
        currentState.copy(
            searchItems = HomeUiState.dummyData
        )
    }

    private fun updateBannerList(loadState: UiState<List<HomeBanner>>) =
        _uiState.update { currentState ->
            currentState.copy(
                bannerImages = loadState
            )
        }

    private fun updateRecommendedProductList(loadState: UiState<List<ProductItem>>) =
        _uiState.update { currentState ->
            currentState.copy(
                recommendedItems = loadState
            )
        }

    private fun updatePopularProductList(loadState: UiState<List<ProductItem>>) =
        _uiState.update { currentState ->
            currentState.copy(
                popularItems = loadState
            )
        }
}