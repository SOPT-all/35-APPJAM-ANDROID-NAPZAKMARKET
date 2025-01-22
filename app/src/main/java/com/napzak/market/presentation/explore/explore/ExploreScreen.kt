package com.napzak.market.presentation.explore.explore

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.presentation.explore.explore.component.ExploreBottomSheetScreen
import com.napzak.market.presentation.explore.explore.component.ExploreFilterGroup
import com.napzak.market.presentation.explore.explore.component.ProductListSection
import com.napzak.market.presentation.explore.explore.component.TradeTypeTab
import com.napzak.market.presentation.explore.explore.state.ExploreBottomSheetState
import com.napzak.market.presentation.explore.explore.state.ExploreUiState
import com.napzak.market.presentation.explore.explore.type.ExploreScreenType

@Composable
fun ExploreRoute(
    searchTerm: String?,
    genreId: Long?,
    onBackButtonClick: () -> Unit,
    onSearchNavigate: (String?) -> Unit,
    onProductDetailNavigate: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState by viewModel.bottomSheetState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        if (uiState.exploreScreenType == ExploreScreenType.BASIC) {
            onBackButtonClick()
        } else {
            onSearchNavigate(uiState.initSearchTerm)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initExploreScreenState(searchTerm, genreId)
    }

    LaunchedEffect(uiState) {
        viewModel.updateExploreProductInformation()
    }

    ExploreScreen(
        modifier = modifier,
        uiState = uiState,
        bottomSheetState = bottomSheetState,
        gridState = gridState,
        debounce = viewModel::debounce,
        onResultBackButtonClick = onSearchNavigate,
        onSearchBoxClick = onSearchNavigate,
        onTradeTypeClick = { tradeType ->
            viewModel.updateTradeType(tradeType)
            viewModel.updateScrollState(coroutineScope, gridState)
        },
        onGenreListClick = {
            viewModel.updateBottomSheetVisibility(BottomSheetType.GENRE_SEARCHING)
        },
        onSoldOutClick = {
            viewModel.updateSale()
            viewModel.updateScrollState(coroutineScope, gridState)
        },
        onUnopenClick = {
            viewModel.updateUnopen()
            viewModel.updateScrollState(coroutineScope, gridState)
        },
        onSortButtonClick = { viewModel.updateBottomSheetVisibility(BottomSheetType.SORT) },
        onItemClick = onProductDetailNavigate,
        onLikeClick = viewModel::updateProductInterest,
        onDismissRequest = viewModel::updateBottomSheetVisibility,
        onSortItemClick = {
            viewModel.updateSortType(it)
            viewModel.updateBottomSheetVisibility(BottomSheetType.SORT)
            viewModel.updateScrollState(coroutineScope, gridState)
        },
        onTextChange = viewModel::changeSearchText,
        onGenreSelectButtonClick = viewModel::updateSelectedGenreList,
    )
}

@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    bottomSheetState: ExploreBottomSheetState,
    gridState: LazyGridState,
    debounce: () -> Unit,
    onResultBackButtonClick: (String?) -> Unit,
    onSearchBoxClick: (String?) -> Unit,
    onTradeTypeClick: (TradeType) -> Unit,
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
    when (uiState.loadState) {
        is UiState.Loading -> {
        }

        is UiState.Empty -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            with(uiState) {
                ExploreSuccessScreen(
                    modifier = modifier,
                    bottomSheetState = bottomSheetState,
                    gridState = gridState,
                    exploreScreenType = exploreScreenType,
                    initSearchTerm = initSearchTerm,
                    tradeType = tradeType,
                    selectedGenreList = selectedGenreList,
                    genreItems = genreItems,
                    isOnSale = isOnSale,
                    isUnopened = isUnopened,
                    productList = uiState.loadState.data.productList,
                    sortType = sortType,
                    debounce = debounce,
                    onBackButtonClick = onResultBackButtonClick,
                    onSearchBoxClick = onSearchBoxClick,
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
fun ExploreSuccessScreen(
    modifier: Modifier = Modifier,
    bottomSheetState: ExploreBottomSheetState,
    gridState: LazyGridState,
    exploreScreenType: ExploreScreenType,
    initSearchTerm: String?,
    tradeType: TradeType,
    selectedGenreList: List<Genre>,
    genreItems: UiState<List<Genre>>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    productList: List<ProductItem>,
    sortType: SortType,
    debounce: () -> Unit,
    onBackButtonClick: (String) -> Unit,
    onSearchBoxClick: (String?) -> Unit,
    onTradeTypeClick: (TradeType) -> Unit,
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
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white)
            .padding(top = 40.dp),
    ) {
        when (exploreScreenType) {
            ExploreScreenType.BASIC -> {
                Box {
                    SearchBox(
                        placeholder = stringResource(id = R.string.explore_search_box_placeholder),
                        readOnly = true,
                        onSearchButtonClick = { onSearchBoxClick(null) },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .noRippleClickable { onSearchBoxClick(null) },
                    )
                }
            }

            ExploreScreenType.WORD_SEARCH_RESULT -> {
                Row(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .noRippleClickable { onBackButtonClick(initSearchTerm.toString()) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_back_24),
                            contentDescription = stringResource(R.string.left_chevron_button),
                            tint = Color.Unspecified,
                        )
                    }
                    SearchBox(
                        placeholder = stringResource(R.string.explore_search_box_placeholder),
                        readOnly = true,
                        searchTerm = initSearchTerm.toString(),
                        onSearchButtonClick = { onSearchBoxClick(initSearchTerm) },
                        modifier = Modifier
                            .noRippleClickable { onSearchBoxClick(initSearchTerm) },
                    )
                }
            }

            ExploreScreenType.GENRE_SEARCH_RESULT -> {
                Row(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .noRippleClickable { onBackButtonClick(selectedGenreList[0].genreName) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_back_24),
                            contentDescription = stringResource(R.string.left_chevron_button),
                            tint = Color.Unspecified,
                        )
                    }
                    SearchBox(
                        placeholder = stringResource(R.string.explore_search_box_placeholder),
                        readOnly = true,
                        onSearchButtonClick = { onSearchBoxClick(selectedGenreList[0].genreName) },
                        modifier = Modifier
                            .noRippleClickable { onSearchBoxClick(selectedGenreList[0].genreName) },
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        TradeTypeTab(
            selectedTab = tradeType,
            onTradeTypeClick = onTradeTypeClick,
        )

        ExploreFilterGroup(
            tradeType = tradeType,
            genreList = selectedGenreList,
            isOnSale = isOnSale,
            isUnopened = isUnopened,
            onGenreListClick = onGenreListClick,
            onSoldOutClick = onSoldOutClick,
            onUnopenClick = onUnopenClick,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    top = 16.dp,
                    end = 20.dp,
                    bottom = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.explore_product),
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.gray900,
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.explore_product_count, productList.size),
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.purple30,
            )

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.noRippleClickable(onSortButtonClick),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = sortType.label,
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray600,
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_down_chevron_16),
                    contentDescription = stringResource(R.string.down_chevron_button),
                    tint = NapzakMarketTheme.colors.gray500,
                )
            }
        }

        ProductListSection(
            gridState = gridState,
            tradeType = tradeType,
            productList = productList,
            onItemClick = onItemClick,
            onLikeClick = onLikeClick,
        )

        Spacer(Modifier.height(20.dp))
    }

    ExploreBottomSheetScreen(
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

@Preview(showBackground = true)
@Composable
private fun ExploreSuccessScreenPreview(modifier: Modifier = Modifier) {
    ExploreSuccessScreen(
        bottomSheetState = ExploreBottomSheetState(
            isSortBottomSheetVisible = false,
            isGenreSearchingBottomSheetVisible = false
        ),
        gridState = LazyGridState(),
        tradeType = TradeType.SELL,
        exploreScreenType = ExploreScreenType.BASIC,
        initSearchTerm = "",
        selectedGenreList = emptyList(),
        genreItems = UiState.Empty,
        isOnSale = false,
        isUnopened = false,
        productList = emptyList(),
        sortType = SortType.RECENT,
        debounce = { },
        onBackButtonClick = { },
        onSearchBoxClick = { },
        onTradeTypeClick = { },
        onGenreListClick = { },
        onSoldOutClick = { },
        onUnopenClick = { },
        onSortButtonClick = { },
        onItemClick = { },
        onLikeClick = { _, _ -> },
        onDismissRequest = { },
        onSortItemClick = { },
        onTextChange = { },
        onGenreSelectButtonClick = { },
    )
}
