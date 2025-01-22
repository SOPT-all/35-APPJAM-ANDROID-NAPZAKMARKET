package com.napzak.market.presentation.marketinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.MarketTab
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.usecase.GenreSearchUseCase
import com.napzak.market.domain.marketinfo.repository.MarketInfoRepository
import com.napzak.market.domain.marketinfo.usecase.GetMarketProductBuyItemsUseCase
import com.napzak.market.domain.marketinfo.usecase.GetMarketProductSellItemsUseCase
import com.napzak.market.presentation.marketinfo.state.MarketInfoBottomSheetState
import com.napzak.market.presentation.marketinfo.state.MarketInfoUiState
import com.napzak.market.presentation.marketinfo.state.MarketProductItemsInformation
import com.napzak.market.presentation.marketinfo.state.MarketUiInformation
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
class MarketInfoViewModel @Inject constructor(
    private val marketInfoRepository: MarketInfoRepository,
    private val getMarketProductSellItemsUseCase: GetMarketProductSellItemsUseCase,
    private val getMarketProductBuyItemsUseCase: GetMarketProductBuyItemsUseCase,
    private val genreSearchUseCase: GenreSearchUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MarketInfoUiState())
    val uiState = _uiState.asStateFlow()

    private val _bottomSheetState: MutableStateFlow<MarketInfoBottomSheetState> =
        MutableStateFlow(MarketInfoBottomSheetState())
    val bottomSheetState: StateFlow<MarketInfoBottomSheetState> = _bottomSheetState.asStateFlow()

    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    fun setStoreId(storeId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                storeId = storeId
            )
        }
    }

    fun getMarketInformation() = viewModelScope.launch {
        marketInfoRepository.fetchMarketInfo(uiState.value.storeId)
            .onSuccess { marketInfo ->
                updateLoadMarketInfoState(
                    UiState.Success(
                        with(marketInfo) {
                            MarketUiInformation(
                                storeNickname = marketInfo.storeNickname,
                                storeDescription = marketInfo.storeDescription,
                                storePhoto = marketInfo.storePhoto,
                                storeCover = marketInfo.storeCover,
                                genrePreferenceList = marketInfo.genrePreferenceList,
                            )
                        }
                    )
                )
            }
            .onFailure { response ->
                updateLoadMarketInfoState(UiState.Failure(response.toString()))
            }
    }

    fun getMarketProductInformation() = viewModelScope.launch {
        with(uiState.value) {
            when (marketTab) {
                MarketTab.SELL -> {
                    getMarketProductSellItemsUseCase(
                        storeId = storeId,
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        isUnopened = isUnopened,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            if (response.isEmpty()) {
                                updateLoadProductItemsState(UiState.Empty)
                            } else {
                                updateLoadProductItemsState(
                                    UiState.Success(
                                        MarketProductItemsInformation(productList = response)
                                    )
                                )
                            }
                        }
                        .onFailure { response ->
                            updateLoadProductItemsState(UiState.Failure(response.toString()))
                        }
                }

                MarketTab.BUY -> {
                    getMarketProductBuyItemsUseCase(
                        storeId = storeId,
                        sortType = sortType.name,
                        isOnSale = isOnSale,
                        genreItems = selectedGenreList,
                    )
                        .onSuccess { response ->
                            if (response.isEmpty()) {
                                updateLoadProductItemsState(UiState.Empty)
                            } else {
                                updateLoadProductItemsState(
                                    UiState.Success(
                                        MarketProductItemsInformation(productList = response)
                                    )
                                )
                            }
                        }
                        .onFailure { response ->
                            updateLoadProductItemsState(UiState.Failure(response.toString()))
                        }
                }

                MarketTab.REVIEW -> {}
            }
        }
    }

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    @OptIn(FlowPreview::class)
    fun debounceSearch() = viewModelScope.launch {
        _searchTerm.debounce(DEBOUNCE_DELAY)
            .collectLatest { debounced ->
                getGenreList(debounced)
            }
    }

    private fun getGenreList(searchTerm: String) = viewModelScope.launch {
        genreSearchUseCase.invoke(searchTerm)
            .onSuccess { response ->
                _uiState.update { currentState ->
                    currentState.copy(
                        genreList = UiState.Success(
                            response
                        )
                    )
                }
            }
            .onFailure { response ->
                _uiState.update { currentState ->
                    currentState.copy(
                        genreList = UiState.Failure(
                            response.message.toString()
                        )
                    )
                }
            }
    }

    fun updateMarketTab(newMarketTab: MarketTab) {
        _uiState.update { currentState ->
            currentState.copy(
                marketTab = newMarketTab,
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

    fun updateSale() {
        _uiState.update { currentState ->
            currentState.copy(
                isOnSale = !uiState.value.isOnSale
            )
        }
    }

    fun updateUnopen() {
        _uiState.update { currentState ->
            currentState.copy(
                isUnopened = !uiState.value.isUnopened
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

    fun updateItemLikeButton(productId: Long) {
        /* TODO: 좋아요 API 연결 및 기능 연결 */
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

    private fun updateLoadMarketInfoState(loadState: UiState<MarketUiInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadMarketInfoState = loadState
            )
        }

    private fun updateLoadProductItemsState(loadState: UiState<MarketProductItemsInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadProductItemsState = loadState
            )
        }

    companion object {
        private const val DEBOUNCE_DELAY = 500L
        private const val MAX_GENRE_SELECTION = 4
    }
}