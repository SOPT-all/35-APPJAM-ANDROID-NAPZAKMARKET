package com.napzak.market.presentation.explore

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.napzak.market.presentation.explore.state.ExploreUiState
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.component.ExploreFilterGroup
import com.napzak.market.presentation.explore.component.ProductListSection
import com.napzak.market.presentation.explore.component.TradeTypeTab
import com.napzak.market.presentation.explore.type.ExploreScreenType
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.presentation.explore.type.TradeType

@Composable
fun ExploreRoute(
    searchTerm: String?,
    genreId: Long?,
    navigatorToSearch: (String?) -> Unit,
    navigatorToProductDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if (searchTerm != null || genreId != null) {
            viewModel.initExploreScreenState(searchTerm, genreId)
        }
        viewModel.getExploreProductInformation()
    }

    ExploreScreen(
        modifier = modifier,
        uiState = uiState,
        onBackButtonClick = navigatorToSearch,
        onSearchBoxClick = navigatorToSearch,
        onTradeTypeClick = viewModel::updateTradeType,
        onGenreListClick = { /* TODO: 장르 검색 bottomSheet */ },
        onSoldOutClick = { viewModel.updateSoldOut() },
        onUnopenClick = { viewModel.updateUnopen() },
        onSortButtonClick = { /* TODO: 정렬 bottomSheet */ },
        onItemClick = { navigatorToProductDetail() },
        onLikeClick = viewModel::updateItemLikeButton,
    )
}

@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onBackButtonClick: (String?) -> Unit,
    onSearchBoxClick: (String?) -> Unit,
    onTradeTypeClick: (String) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
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
                    exploreScreenType = exploreScreenType,
                    initSearchTerm = initSearchTerm,
                    tradeType = tradeType,
                    genreList = genreList,
                    isOnSale = isOnSale,
                    isUnopened = isUnopened,
                    productList = uiState.loadState.data.productList,
                    sortType = sortType,
                    onBackButtonClick = onBackButtonClick,
                    onSearchBoxClick = onSearchBoxClick,
                    onTradeTypeClick = onTradeTypeClick,
                    onGenreListClick = onGenreListClick,
                    onSoldOutClick = onSoldOutClick,
                    onUnopenClick = onUnopenClick,
                    onSortButtonClick = onSortButtonClick,
                    onItemClick = onItemClick,
                    onLikeClick = onLikeClick,
                )
            }
        }
    }
}

@Composable
fun ExploreSuccessScreen(
    modifier: Modifier = Modifier,
    exploreScreenType: String,
    initSearchTerm: String?,
    tradeType: String,
    genreList: List<Genre>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    productList: List<ProductItem>,
    sortType: String,
    onBackButtonClick: (String) -> Unit,
    onSearchBoxClick: (String?) -> Unit,
    onTradeTypeClick: (String) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white)
            .padding(top = 40.dp),
    ) {
        when (exploreScreenType) {
            ExploreScreenType.BASIC.name -> {
                Box {
                    SearchBox(
                        placeholder = stringResource(id = R.string.explore_search_box_placeholder),
                        readOnly = true,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .noRippleClickable { onSearchBoxClick(null) },
                    )
                }

            }

            ExploreScreenType.WORD_SEARCH_RESULT.name -> {
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
                        modifier = Modifier
                            .noRippleClickable { onSearchBoxClick(initSearchTerm) },
                    )
                }
            }

            ExploreScreenType.GENRE_SEARCH_RESULT.name -> {
                Row(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .noRippleClickable { onBackButtonClick(genreList[0].genreName) },
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
                        modifier = Modifier
                            .noRippleClickable { onSearchBoxClick(genreList[0].genreName) },
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
            genreList = genreList,
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
                    text = SortType.getSortLabel(sortType),
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
            tradeType = tradeType,
            productList = productList,
            onItemClick = onItemClick,
            onLikeClick = onLikeClick,
        )

        Spacer(Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ExploreSuccessScreenPreview(modifier: Modifier = Modifier) {
    ExploreSuccessScreen(
        tradeType = TradeType.SELL.name,
        exploreScreenType = ExploreScreenType.BASIC.name,
        initSearchTerm = "",
        genreList = emptyList(),
        isOnSale = false,
        isUnopened = false,
        productList = emptyList(),
        sortType = SortType.RECENT.name,
        onBackButtonClick = { },
        onSearchBoxClick = { },
        onTradeTypeClick = { },
        onGenreListClick = { },
        onSoldOutClick = { },
        onUnopenClick = { },
        onSortButtonClick = { },
        onItemClick = { },
        onLikeClick = { },
    )
}