package com.napzak.market.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.napzak.market.presentation.explore.component.TradeTypeTab
import com.napzak.market.presentation.explore.type.TradeType

@Composable
fun ExploreRoute(
    onSearchBoxClick: () -> Unit,
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
                    onSearchBoxClick = onSearchBoxClick,
                    onTradeTypeClick = onTradeTypeClick,
                    onGenreListClick = onGenreListClick,
                    onSoldOutClick = onSoldOutClick,
                    onUnopenClick = onUnopenClick,
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
    onSearchBoxClick: () -> Unit,
    onTradeTypeClick: (String) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white)
            .padding(top = 40.dp)
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
        onSearchBoxClick = { /* 검색화면으로 이동 */ },
        onTradeTypeClick = { /* 팔아요/구해요 탭 변경 */ },
        onGenreListClick = { },
        onSoldOutClick = { },
        onUnopenClick = { },
    )
}