package com.napzak.market.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.banner.model.HomeBanner
import com.napzak.market.domain.banner.repository.HomeBannerRepository
import com.napzak.market.domain.interest.repository.InterestRepository
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.repository.HomeProductRepository
import com.napzak.market.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bannerRepository: HomeBannerRepository,
    private val productRepository: HomeProductRepository,
    private val interestRepository: InterestRepository,
) : ViewModel() {
    private val _bannerLoadState = MutableStateFlow<UiState<List<HomeBanner>>>(UiState.Loading)
    private val _recommendProductLoadState =
        MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    private val _popularProductLoadState =
        MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    private val _buyProductLoadState = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)

    val uiState: StateFlow<HomeUiState> = combine(
        _bannerLoadState,
        _recommendProductLoadState,
        _popularProductLoadState,
        _buyProductLoadState,
    ) { banner, recommend, popular, buy ->
        HomeUiState(
            bannerImages = banner,
            recommendedItems = recommend,
            popularItems = popular,
            searchItems = buy,
            isLoaded = isLoaded()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(),
    )

    suspend fun loadItems() {
        getRecommendedItems()
        getPopularItems()
        getMostSearchedItems()
    }

    fun getBannerImages() = viewModelScope.launch {
        bannerRepository.fetchHomeBannerList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    _bannerLoadState.update { UiState.Empty }
                } else {
                    _bannerLoadState.update { UiState.Success(response) }
                }
            }
            .onFailure { response ->
                _bannerLoadState.update { UiState.Failure(response.message.toString()) }
            }
    }

    private suspend fun getRecommendedItems() {
        productRepository.fetchRecommendProductList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    _recommendProductLoadState.update { UiState.Empty }
                } else {
                    _recommendProductLoadState.update { UiState.Success(response) }
                }
            }
            .onFailure { response ->
                _recommendProductLoadState.update { UiState.Failure(response.message.toString()) }
            }
    }

    private suspend fun getPopularItems() {
        productRepository.fetchPopularProductList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    _popularProductLoadState.update { UiState.Empty }
                } else {
                    _popularProductLoadState.update { UiState.Success(response) }
                }
            }
            .onFailure { response ->
                _popularProductLoadState.update { UiState.Failure(response.message.toString()) }
            }
    }

    private suspend fun getMostSearchedItems() {
        productRepository.fetchBuyProductList()
            .onSuccess { response ->
                if (response.isEmpty()) {
                    _buyProductLoadState.update { UiState.Empty }
                } else {
                    _buyProductLoadState.update { UiState.Success(response) }
                }
            }
            .onFailure { response ->
                _buyProductLoadState.update { UiState.Failure(response.message.toString()) }
            }
    }

    fun setProductInterest(productId: Long, isInterested: Boolean) {
        if (isInterested) {
            deleteInterest(productId)
        } else {
            postInterest(productId)
        }
    }

    private fun postInterest(productId: Long) = viewModelScope.launch {
        interestRepository.postInterest(productId)
            .onSuccess {
                loadItems()
            }
    }

    private fun deleteInterest(productId: Long) = viewModelScope.launch {
        interestRepository.deleteInterest(productId)
            .onSuccess {
                loadItems()
            }
    }

    private fun isLoaded(): UiState<Unit> =
        when {
            _bannerLoadState.value is UiState.Success
                    && _recommendProductLoadState.value is UiState.Success
                    && _popularProductLoadState.value is UiState.Success
                    && _buyProductLoadState.value is UiState.Success -> UiState.Success(Unit)

            _bannerLoadState.value is UiState.Failure
                    || _recommendProductLoadState.value is UiState.Failure
                    || _popularProductLoadState.value is UiState.Failure
                    || _buyProductLoadState.value is UiState.Failure -> UiState.Failure("failed to load data")

            _bannerLoadState.value is UiState.Loading
                    || _recommendProductLoadState.value is UiState.Loading
                    || _popularProductLoadState.value is UiState.Loading
                    || _buyProductLoadState.value is UiState.Loading -> UiState.Loading

            else -> UiState.Empty
        }

}