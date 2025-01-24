package com.napzak.market.presentation.explore.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.usecase.GenreSearchUseCase
import com.napzak.market.domain.interest.repository.InterestRepository
import com.napzak.market.domain.product.usecase.GetProductBuyListUseCase
import com.napzak.market.domain.product.usecase.GetProductSellListUseCase
import com.napzak.market.domain.product.usecase.GetSearchedProductBuyItemsUseCase
import com.napzak.market.domain.product.usecase.GetSearchedProductSellItemsUseCase
import com.napzak.market.presentation.explore.explore.state.ExploreBottomSheetState
import com.napzak.market.presentation.explore.explore.state.ExploreProductInformation
import com.napzak.market.presentation.explore.explore.state.ExploreUiState
import com.napzak.market.presentation.explore.explore.type.ExploreScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getProductSellListUseCase: GetProductSellListUseCase,
    private val getProductBuyListUseCase: GetProductBuyListUseCase,
    private val getSearchedProductSellItemsUseCase: GetSearchedProductSellItemsUseCase,
    private val getSearchedProductBuyItemsUseCase: GetSearchedProductBuyItemsUseCase,
    private val genreSearchUseCase: GenreSearchUseCase,
    private val interestRepository: InterestRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    private val _bottomSheetState: MutableStateFlow<ExploreBottomSheetState> =
        MutableStateFlow(ExploreBottomSheetState())
    val bottomSheetState: StateFlow<ExploreBottomSheetState> = _bottomSheetState.asStateFlow()

    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    fun initExploreScreenState(
        searchTerm: String?,
        genreId: Long?,
    ) {
        val exploreScreenType = when {
            searchTerm != null && genreId != null -> {
                initSelectedGenreList(searchTerm, genreId)
                ExploreScreenType.GENRE_SEARCH_RESULT
            }

            searchTerm != null -> {
                initSearchTerm(searchTerm)
                ExploreScreenType.WORD_SEARCH_RESULT
            }

            else -> {
                initSelectedGenreList(null, null)
                initSearchTerm(null)
                ExploreScreenType.BASIC
            }
        }

        updateScreenType(exploreScreenType)
    }

    private fun updateScreenType(exploreScreenType: ExploreScreenType) {
        _uiState.update { currentState ->
            currentState.copy(
                exploreScreenType = exploreScreenType,
                tradeType = TradeType.SELL,
                sortType = SortType.RECENT,
            )
        }
    }

    private fun initSelectedGenreList(
        genreName: String?,
        genreId: Long?,
    ) {
        _uiState.update { currentState ->
            if (genreName == null) {
                currentState.copy(
                    selectedGenreList = emptyList(),
                )
            } else {
                currentState.copy(
                    selectedGenreList = listOf(
                        Genre(
                            genreId = genreId ?: 0,
                            genreName = genreName,
                        )
                    ),
                )
            }
        }
    }

    private fun initSearchTerm(searchTerm: String?) {
        _uiState.update { currentState ->
            currentState.copy(
                initSearchTerm = searchTerm,
            )
        }
    }

    fun updateExploreProductInformation() = viewModelScope.launch {
        with(uiState.value) {
            when {
                tradeType == TradeType.SELL && initSearchTerm == null -> {
                    getProductSellListUseCase(
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        isUnopened = isUnopened,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            updateLoadState(
                                UiState.Success(
                                    ExploreProductInformation(productList = response)
                                )
                            )
                        }
                        .onFailure { response ->
                            updateLoadState(UiState.Failure(response.toString()))
                        }
                }

                tradeType == TradeType.BUY && initSearchTerm == null -> {
                    getProductBuyListUseCase(
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            updateLoadState(
                                UiState.Success(
                                    ExploreProductInformation(productList = response)
                                )
                            )
                        }
                        .onFailure { response ->
                            updateLoadState(UiState.Failure(response.toString()))
                        }
                }

                tradeType == TradeType.SELL && initSearchTerm != null -> {
                    getSearchedProductSellItemsUseCase(
                        searchTerm = initSearchTerm,
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        isUnopened = isUnopened,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            updateLoadState(
                                UiState.Success(
                                    ExploreProductInformation(productList = response)
                                )
                            )
                        }
                        .onFailure { response ->
                            updateLoadState(UiState.Failure(response.toString()))
                        }
                }

                tradeType == TradeType.BUY && initSearchTerm != null -> {
                    getSearchedProductBuyItemsUseCase(
                        searchTerm = initSearchTerm,
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            updateLoadState(
                                UiState.Success(
                                    ExploreProductInformation(productList = response)
                                )
                            )
                        }
                        .onFailure { response ->
                            updateLoadState(UiState.Failure(response.toString()))
                        }
                }
            }
        }
    }

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    @OptIn(FlowPreview::class)
    fun debounce() = viewModelScope.launch {
        _searchTerm.debounce(DEBOUNCE_DELAY)
            .collectLatest { debounced ->
                getGenres(debounced)
            }
    }

    private fun getGenres(searchTerm: String) = viewModelScope.launch {
        genreSearchUseCase(searchTerm)
            .onSuccess { response ->
                _uiState.update { currentState ->
                    currentState.copy(
                        genreItems = UiState.Success(
                            response
                        )
                    )
                }
            }
            .onFailure { response ->
                _uiState.update { currentState ->
                    currentState.copy(
                        genreItems = UiState.Failure(
                            response.message.toString()
                        )
                    )
                }
            }
    }

    fun updateTradeType(newTradeType: TradeType) {
        _uiState.update { currentState ->
            currentState.copy(
                tradeType = newTradeType,
                sortType = SortType.RECENT
            )
        }
    }

    fun updateSelectedGenreList(newSelectedGenreList: List<Genre>) {
        if (_uiState.value.selectedGenreList.size <= MAX_GENRE_SELECTION) {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedGenreList = newSelectedGenreList
                )
            }
        }
    }

    fun updateSale(newValue: Boolean? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                isOnSale = newValue ?: !uiState.value.isOnSale
            )
        }
    }

    fun updateUnopen(newValue: Boolean? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                isUnopened = newValue ?: !uiState.value.isUnopened
            )
        }
    }

    fun updateSortType(newSortType: SortType) {
        _uiState.update { currentState ->
            currentState.copy(
                sortType = newSortType
            )
        }
    }

    fun updateProductInterest(
        productId: Long,
        isInterested: Boolean,
    ) {
        if (isInterested) {
            deleteInterest(productId)
        } else {
            addInterest(productId)
        }
    }

    private fun addInterest(productId: Long) = viewModelScope.launch {
        interestRepository.postInterest(productId)
            .onSuccess {
                updateExploreProductInformation()
            }
    }

    private fun deleteInterest(productId: Long) = viewModelScope.launch {
        interestRepository.deleteInterest(productId)
            .onSuccess {
                updateExploreProductInformation()
            }
    }

    fun updateBottomSheetVisibility(type: BottomSheetType) {
        when (type) {
            BottomSheetType.SORT -> {
                _bottomSheetState.update {
                    it.copy(isSortBottomSheetVisible = !_bottomSheetState.value.isSortBottomSheetVisible)
                }
            }

            BottomSheetType.GENRE_SEARCHING -> {
                _bottomSheetState.update {
                    it.copy(isGenreSearchingBottomSheetVisible = !_bottomSheetState.value.isGenreSearchingBottomSheetVisible)
                }
            }
        }
    }

    private fun updateLoadState(loadState: UiState<ExploreProductInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }

    companion object {
        private const val DEBOUNCE_DELAY = 500L
        private const val MAX_GENRE_SELECTION = 4
    }
}