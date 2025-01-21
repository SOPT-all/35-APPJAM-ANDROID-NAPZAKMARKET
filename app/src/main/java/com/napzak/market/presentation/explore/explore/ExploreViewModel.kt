package com.napzak.market.presentation.explore.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.repository.ExploreRepository
import com.napzak.market.domain.genre.model.Genre
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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val exploreRepository: ExploreRepository,
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

        updateUiState(exploreScreenType)
    }

    private fun updateUiState(exploreScreenType: ExploreScreenType) { // TODO: 함수명 변경 필요
        _uiState.update { currentState ->
            currentState.copy(
                exploreScreenType = exploreScreenType
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

    fun initGenreList() {
        /* TODO: 장르 리스트 조회 API 연결 */
        _uiState.update { currentState ->
            currentState.copy(
                initGenreList = listOf(
                    Genre(
                        genreId = 1,
                        genreName = "나루토",
                    ),
                    Genre(
                        genreId = 2,
                        genreName = "원피스",
                    ),
                    Genre(
                        genreId = 3,
                        genreName = "블리치",
                    ),
                    Genre(
                        genreId = 4,
                        genreName = "귀멸의 칼날",
                    ),
                    Genre(
                        genreId = 5,
                        genreName = "주술회전",
                    ),
                    Genre(
                        genreId = 6,
                        genreName = "진격의 거인",
                    ),
                    Genre(
                        genreId = 7,
                        genreName = "데스노트",
                    ),
                    Genre(
                        genreId = 8,
                        genreName = "짱구는 못말려",
                    ),
                    Genre(
                        genreId = 9,
                        genreName = "도라에몽",
                    ),
                    Genre(
                        genreId = 10,
                        genreName = "강철의 연금술사",
                    ),
                    Genre(
                        genreId = 11,
                        genreName = "체인소맨",
                    ),
                    Genre(
                        genreId = 12,
                        genreName = "원펀맨",
                    ),
                    Genre(
                        genreId = 13,
                        genreName = "드래곤볼",
                    ),
                    Genre(
                        genreId = 14,
                        genreName = "명탐정 코난",
                    ),
                    Genre(
                        genreId = 15,
                        genreName = "슬램덩크",
                    )
                )
            )
        }
    }

    fun getExploreProductInformation() = viewModelScope.launch {
        val productListFilter = with(uiState.value) {
            ProductListFilter(
                sortOption = sortType.name,
                genreId = selectedGenreList.getIdFromGenreList(),
                isOnSale = isOnSale,
                isUnopened = isUnopened,
            )
        }

        if (uiState.value.tradeType == TradeType.SELL) {
            exploreRepository.fetchSellProductItemList(productListFilter)
                .onSuccess { response ->
                    if (response.isEmpty()) {
                        updateLoadState(UiState.Empty)
                    } else {
                        updateLoadState(
                            UiState.Success(
                                ExploreProductInformation(productList = response)
                            )
                        )
                    }
                }
                .onFailure { response ->
                    Timber.e(response)
                }
        } else {
            exploreRepository.fetchBuyProductItemList(productListFilter)
                .onSuccess { response ->
                    if (response.isEmpty()) {
                        updateLoadState(UiState.Empty)
                    } else {
                        updateLoadState(
                            UiState.Success(
                                ExploreProductInformation(productList = response)
                            )
                        )
                    }
                }
                .onFailure { response ->
                    Timber.e(response)
                }
        }
    }

    fun changeSearchText(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
        debounceSearch()
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    @OptIn(FlowPreview::class)
    private suspend fun debounceSearch() = _searchTerm.debounce(DEBOUNCE_DELAY)
        .collectLatest { debounced ->
            getGenreList(debounced)
        }

    // TODO: 서버 통신으로 대체
    private fun getGenreList(searchTerm: String) {
        _uiState.update { currentState ->
            currentState.copy(
                genreList = if (searchTerm.isEmpty()) {
                    uiState.value.initGenreList
                } else {
                    uiState.value.initGenreList.filter {
                        it.genreName.contains(searchTerm)
                    }
                }
            )
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

private fun List<Genre>.getIdFromGenreList(): List<Long>? {
    return if (this.isNotEmpty()) this.map { it.genreId } else null
}