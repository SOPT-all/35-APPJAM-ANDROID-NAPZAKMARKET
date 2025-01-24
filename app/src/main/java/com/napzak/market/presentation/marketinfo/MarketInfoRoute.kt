package com.napzak.market.presentation.marketinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.image.EmptyImage
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.MarketTab
import com.napzak.market.core.type.SortType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.marketinfo.component.MarketFilterGroup
import com.napzak.market.presentation.marketinfo.component.MarketInfoBottomSheetScreen
import com.napzak.market.presentation.marketinfo.component.MarketInfoTopSection
import com.napzak.market.presentation.marketinfo.component.MarketProductListSection
import com.napzak.market.presentation.marketinfo.component.MarketTradeTypeTab
import com.napzak.market.presentation.marketinfo.state.MarketInfoBottomSheetState
import com.napzak.market.presentation.marketinfo.state.MarketInfoUiState
import com.napzak.market.presentation.marketinfo.state.MarketProductItemsInformation
import com.napzak.market.presentation.marketinfo.state.MarketUiInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MarketInfoRoute(
    storeId: Long,
    onDetailPageNavigate: (Long) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MarketInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState by viewModel.bottomSheetState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.setStoreId(storeId)
        viewModel.updateMarketInformation()
    }

    LaunchedEffect(uiState) {
        viewModel.updateMarketProductInformation()
    }

    MarketInfoScreen(
        modifier = modifier,
        uiState = uiState,
        bottomSheetState = bottomSheetState,
        gridState = gridState,
        debounce = viewModel::debounce,
        onBackButtonClick = onBackButtonClick,
        onTradeTypeClick = { tradeType ->
            viewModel.updateMarketTab(tradeType)
            updateScrollState(coroutineScope, gridState)
        },
        onGenreListClick = {
            viewModel.updateBottomSheetVisibility(BottomSheetType.GENRE_SEARCHING)
        },
        onSoldOutClick = {
            viewModel.updateSale()
            updateScrollState(coroutineScope, gridState)
        },
        onUnopenClick = {
            viewModel.updateUnopen()
            updateScrollState(coroutineScope, gridState)
        },
        onSortButtonClick = { viewModel.updateBottomSheetVisibility(BottomSheetType.SORT) },
        onItemClick = onDetailPageNavigate,
        onLikeClick = viewModel::updateProductInterest,
        onDismissRequest = viewModel::updateBottomSheetVisibility,
        onSortItemClick = {
            viewModel.updateSortType(it)
            viewModel.updateBottomSheetVisibility(BottomSheetType.SORT)
            updateScrollState(coroutineScope, gridState)
        },
        onTextChange = viewModel::changeSearchText,
        onGenreSelectButtonClick = viewModel::updateSelectedGenreList,
    )
}

@Composable
fun MarketInfoScreen(
    uiState: MarketInfoUiState,
    bottomSheetState: MarketInfoBottomSheetState,
    gridState: LazyGridState,
    debounce: () -> Unit,
    onBackButtonClick: () -> Unit,
    onTradeTypeClick: (MarketTab) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    onLikeClick: (Long, Boolean) -> Unit,
    onDismissRequest: (BottomSheetType) -> Unit,
    onSortItemClick: (SortType) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.loadMarketInfoState) {
        is UiState.Loading -> {}

        is UiState.Empty -> {}

        is UiState.Failure -> {}

        is UiState.Success -> {
            with(uiState) {
                MarketInfoSuccessScreen(
                    modifier = modifier,
                    bottomSheetState = bottomSheetState,
                    gridState = gridState,
                    marketTab = marketTab,
                    selectedGenreList = selectedGenreList,
                    genreItems = genreItems,
                    isOnSale = isOnSale,
                    isUnopened = isUnopened,
                    marketInfo = uiState.loadMarketInfoState.data,
                    loadProductState = uiState.loadProductItemsState,
                    sortType = sortType,
                    debounce = debounce,
                    onBackButtonClick = onBackButtonClick,
                    onTradeTypeClick = onTradeTypeClick,
                    onGenreListClick = onGenreListClick,
                    onSoldOutClick = onSoldOutClick,
                    onUnopenClick = onUnopenClick,
                    onSortButtonClick = onSortButtonClick,
                    onItemClick = onItemClick,
                    onLikeClick = onLikeClick,
                    onDismissRequest = onDismissRequest,
                    onSortItemClick = onSortItemClick,
                    onTextChange = onTextChange,
                    onGenreSelectButtonClick = onGenreSelectButtonClick,
                )
            }
        }
    }
}

@Composable
fun MarketInfoSuccessScreen(
    bottomSheetState: MarketInfoBottomSheetState,
    gridState: LazyGridState,
    marketTab: MarketTab,
    selectedGenreList: List<Genre>,
    genreItems: UiState<List<Genre>>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    marketInfo: MarketUiInformation,
    loadProductState: UiState<MarketProductItemsInformation>,
    sortType: SortType,
    debounce: () -> Unit,
    onBackButtonClick: () -> Unit,
    onTradeTypeClick: (MarketTab) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    onLikeClick: (Long, Boolean) -> Unit,
    onDismissRequest: (BottomSheetType) -> Unit,
    onSortItemClick: (SortType) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white),
    ) {
        MarketInfoTopSection(
            marketInfo = marketInfo,
            onBackButtonClick = onBackButtonClick,
        )

        MarketTradeTypeTab(
            itemPadding = 12.dp,
            selectedTab = marketTab,
            onTradeTypeClick = onTradeTypeClick,
        )

        Spacer(Modifier.height(1.dp))

        if (marketTab != MarketTab.REVIEW) {
            MarketFilterGroup(
                marketTab = marketTab,
                genreList = selectedGenreList,
                isOnSale = isOnSale,
                isUnopened = isUnopened,
                onGenreListClick = onGenreListClick,
                onSoldOutClick = onSoldOutClick,
                onUnopenClick = onUnopenClick,
            )

            when (loadProductState) {
                is UiState.Empty -> {}
                is UiState.Loading -> {}
                is UiState.Failure -> {}
                is UiState.Success -> {
                    MarketProductListSection(
                        gridState = gridState,
                        tradeType = marketTab,
                        sortType = sortType,
                        productList = loadProductState.data.productList,
                        onSortButtonClick = onSortButtonClick,
                        onItemClick = onItemClick,
                        onLikeClick = onLikeClick,
                    )

                    Spacer(Modifier.height(20.dp))
                }
            }
        } else {
            EmptyImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }

    MarketInfoBottomSheetScreen(
        bottomSheetState = bottomSheetState,
        selectedGenreList = selectedGenreList,
        genreItems = genreItems,
        sortType = sortType,
        debounce = debounce,
        onDismissRequest = onDismissRequest,
        onSortItemClick = onSortItemClick,
        onTextChange = onTextChange,
        onGenreSelectButtonClick = onGenreSelectButtonClick,
    )
}

private fun updateScrollState(
    coroutineScope: CoroutineScope,
    gridState: LazyGridState,
) {
    coroutineScope.launch {
        gridState.scrollToItem(0)
    }
}

@Preview
@Composable
private fun MarketPreview(modifier: Modifier = Modifier) {
    MarketInfoSuccessScreen(
        bottomSheetState = MarketInfoBottomSheetState(),
        gridState = LazyGridState(),
        marketTab = MarketTab.BUY,
        selectedGenreList = emptyList(),
        genreItems = UiState.Empty,
        isOnSale = false,
        isUnopened = false,
        marketInfo = MarketUiInformation(
            storeNickname = "123",
            storeDescription = "asdf",
            storePhoto = "",
            storeCover = "",
            genrePreferenceList = emptyList(),
        ),
        loadProductState = UiState.Empty,
        sortType = SortType.RECENT,
        debounce = { },
        onBackButtonClick = { },
        onTradeTypeClick = { },
        onGenreListClick = {},
        onSoldOutClick = {},
        onUnopenClick = { },
        onSortButtonClick = { },
        onItemClick = { },
        onLikeClick = { _, _ -> },
        onDismissRequest = { },
        onSortItemClick = {},
        onTextChange = {},
        onGenreSelectButtonClick = {},
        modifier = Modifier,
    )
}