package com.napzak.market.presentation.marketinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.SortType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.type.ExploreBottomSheetType
import com.napzak.market.presentation.marketinfo.component.MarketFilterGroup
import com.napzak.market.presentation.marketinfo.component.MarketInfoBottomSheetScreen
import com.napzak.market.presentation.marketinfo.component.MarketInfoTopSection
import com.napzak.market.presentation.marketinfo.component.MarketProductListSection
import com.napzak.market.presentation.marketinfo.component.MarketTradeTypeTab
import com.napzak.market.presentation.marketinfo.state.MarketInfoBottomSheetState
import com.napzak.market.presentation.marketinfo.state.MarketInfoUiState
import com.napzak.market.presentation.marketinfo.state.MarketUiInformation
import kotlin.String

@Composable
fun MarketInfoRoute(
    storeId: Long,
    onProductDetailNavigate: () -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MarketInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState by viewModel.bottomSheetState.collectAsStateWithLifecycle()

    viewModel.setStoreId(storeId)

    LaunchedEffect(uiState) {
        viewModel.getMarketInformation()
    }

    MarketInfoScreen(
        modifier = modifier,
        uiState = uiState,
        bottomSheetState = bottomSheetState,
        onBackButtonClick = onBackButtonClick,
        onTradeTypeClick = viewModel::updateTradeType,
        onGenreListClick = {
            viewModel.initGenreList()
            viewModel.updateBottomSheetVisibility(ExploreBottomSheetType.GENRE_SEARCHING)
        },
        onSoldOutClick = { viewModel.updateSoldOut() },
        onUnopenClick = { viewModel.updateUnopen() },
        onSortButtonClick = { viewModel.updateBottomSheetVisibility(ExploreBottomSheetType.SORT) },
        onItemClick = { onProductDetailNavigate() },
        onLikeClick = viewModel::updateItemLikeButton,
        onDismissRequest = { viewModel.updateBottomSheetVisibility(it) },
        onSortItemClick = {
            viewModel.updateSortType(it)
            viewModel.updateBottomSheetVisibility(ExploreBottomSheetType.SORT)
        },
        onTextChange = { viewModel.changeSearchText(it) },
        onGenreSelectButtonClick = viewModel::updateSelectedGenreList,
    )
}

@Composable
fun MarketInfoScreen(
    uiState: MarketInfoUiState,
    bottomSheetState: MarketInfoBottomSheetState,
    onBackButtonClick: () -> Unit,
    onTradeTypeClick: (TradeType) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
    onDismissRequest: (ExploreBottomSheetType) -> Unit,
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
                MarketInfoSuccessScreen(
                    modifier = modifier,
                    bottomSheetState = bottomSheetState,
                    tradeType = tradeType,
                    selectedGenreList = selectedGenreList,
                    genreList = genreList,
                    initialGenreList = initGenreList,
                    isOnSale = isOnSale,
                    isUnopened = isUnopened,
                    marketInfo = uiState.loadState.data,
                    sortType = sortType,
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
    tradeType: TradeType,
    selectedGenreList: List<Genre>,
    initialGenreList: List<Genre>,
    genreList: List<Genre>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    marketInfo: MarketUiInformation,
    sortType: SortType,
    onBackButtonClick: () -> Unit,
    onTradeTypeClick: (TradeType) -> Unit,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    onSortButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
    onDismissRequest: (ExploreBottomSheetType) -> Unit,
    onSortItemClick: (SortType) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white)
            .padding(top = 40.dp),
    ) {
        MarketInfoTopSection(
            marketInfo = marketInfo,
            onBackButtonClick = onBackButtonClick,
        )

        MarketTradeTypeTab(
            tradeTypeList = listOf(TradeType.SELL, TradeType.BUY, TradeType.REVIEW),
            itemPadding = 12.dp,
            selectedTab = tradeType,
            onTradeTypeClick = onTradeTypeClick,
        )

        if (tradeType != TradeType.REVIEW) {
            MarketFilterGroup(
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
                    text = stringResource(
                        id = R.string.explore_product_count,
                        marketInfo.productList.size
                    ),
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

            MarketProductListSection(
                tradeType = tradeType,
                productList = marketInfo.productList,
                onItemClick = onItemClick,
                onLikeClick = onLikeClick,
            )

            Spacer(Modifier.height(20.dp))
        } else {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.img_ready),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.ready_notice),
                    style = NapzakMarketTheme.typography.titleSemi20,
                    color = NapzakMarketTheme.colors.gray800,
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.ready_wait_please),
                    style = NapzakMarketTheme.typography.bodyMedium14,
                    color = NapzakMarketTheme.colors.gray600,
                )
            }
        }
    }

    MarketInfoBottomSheetScreen(
        bottomSheetState = bottomSheetState,
        selectedGenreList = selectedGenreList,
        initialGenreList = initialGenreList,
        genreList = genreList,
        sortType = sortType,
        onDismissRequest = onDismissRequest,
        onSortItemClick = onSortItemClick,
        onTextChange = onTextChange,
        onGenreSelectButtonClick = onGenreSelectButtonClick,
    )
}