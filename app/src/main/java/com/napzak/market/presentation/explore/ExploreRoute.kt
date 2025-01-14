package com.napzak.market.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.state.ExploreUiState
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.explore.component.ExploreFilterGroup
import com.napzak.market.presentation.explore.component.ProductListSection
import com.napzak.market.presentation.explore.component.TradeTypeTab
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.presentation.explore.type.TradeType
import com.napzak.market.presentation.explore.type.getSortLabel

@Composable
fun ExploreRoute(
    onSearchBoxClick: () -> Unit,
    navigateToProductDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getExploreProductInformation()
    }

    ExploreScreen(
        modifier = modifier,
        uiState = uiState,
        onSearchBoxClick = onSearchBoxClick,
        onTradeTypeClick = { viewModel.changeTradeType(it) },
        onGenreListClick = { /* 장르 검색 bottomSheet */ },
        onSoldOutClick = { viewModel.changeSoldOut() },
        onUnopenClick = { viewModel.changeUnopen() },
        onSortButtonClick = { /* 정렬 bottomSheet */ },
        onItemClick = { navigateToProductDetail() },
        onLikeClick = { viewModel.changeItemLikeButton(it) },
    )
}

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    uiState: ExploreUiState,
    onSearchBoxClick: () -> Unit,
    onTradeTypeClick: (String) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
) {
    when (uiState.loadState) {
        is UiState.Loading -> {
        }

        is UiState.Empty -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            with(uiState.loadState.data) {
                ExploreSuccessScreen(
                    modifier = modifier,
                    tradeType = uiState.tradeType,
                    genreList = uiState.genreList,
                    isOnSale = uiState.isOnSale,
                    isUnopened = uiState.isUnopened,
                    productList = uiState.loadState.data.productList,
                    sortType = uiState.sortType,
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
    tradeType: String,
    genreList: List<String>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    productList: List<ProductItem>,
    sortType: String,
    onSearchBoxClick: () -> Unit,
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
            .padding(vertical = 40.dp)
    ) {
        SearchBox(
            placeholder = stringResource(id = R.string.explore_search_box_placeholder),
            readOnly = true,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .noRippleClickable { onSearchBoxClick() },
        )

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
                .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.explore_product),
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.gray900
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.explore_product_count, productList.size),
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.purple30
            )

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.noRippleClickable { onSortButtonClick() },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = getSortLabel(sortType),
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray600
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_down_chevron_16),
                    contentDescription = stringResource(R.string.down_chevron_button),
                    tint = NapzakMarketTheme.colors.gray500
                )
            }
        }

        ProductListSection(
            tradeType = tradeType,
            productList = productList,
            onItemClick = onItemClick,
            onLikeClick = onLikeClick,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ExploreSuccessScreenPreview(modifier: Modifier = Modifier) {
    ExploreSuccessScreen(
        tradeType = TradeType.SELL.name,
        genreList = emptyList(),
        isOnSale = false,
        isUnopened = false,
        productList = emptyList(),
        sortType = SortType.RECENT.name,
        onSearchBoxClick = { /* 검색화면으로 이동 */ },
        onTradeTypeClick = { /* 팔아요/구해요 탭 변경 */ },
        onGenreListClick = { },
        onSoldOutClick = { },
        onUnopenClick = { },
        onSortButtonClick = { },
        onItemClick = { },
        onLikeClick = { },
    )
}