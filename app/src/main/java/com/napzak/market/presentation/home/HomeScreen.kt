package com.napzak.market.presentation.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.snackbar.CommonSnackBar
import com.napzak.market.core.designsystem.component.topbar.NapzakLogoTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem
import com.napzak.market.presentation.home.component.HomeBannerPager
import com.napzak.market.presentation.home.component.HomeMostSearchedItemGroup
import com.napzak.market.presentation.home.component.HomePopularItemGroup
import com.napzak.market.presentation.home.component.HomeRecommendationItemGroup
import com.napzak.market.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onDetailPageNavigate: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        with(viewModel) {
            loadItems()
            getBannerImages()
        }
    }

    HomeScreen(
        uiState = uiState,
        onLikeClick = viewModel::setProductInterest,
        onItemClick = onDetailPageNavigate,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onLikeClick: (Long, Boolean) -> Unit,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime by remember { mutableLongStateOf(0L) }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = backPressedState) {
        if (System.currentTimeMillis() - backPressedTime <= 2000) {
            (context as Activity).finish()
        } else {
            backPressedState = true
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = "버튼을 한 번 더 누르면 종료돼요",
                    duration = SnackbarDuration.Short
                )
            }
        }
        backPressedTime = System.currentTimeMillis()
    }


    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
    ) {
        Box(
            modifier = modifier.background(
                color = NapzakMarketTheme.colors.white,
            ),
        ) {
            LazyColumn {
                stickyHeader {
                    NapzakLogoTopBar()
                }

                item {

                    HomeUiStateGroup(
                        uiState = uiState.isLoaded,
                        success = {
                            HomeBannerPager(
                                bannerImages = (uiState.bannerImages as UiState.Success).data.toImmutableList(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(360f / 230f),
                            )

                            HomeRecommendationItemGroup(
                                recommendedItems = (uiState.recommendedItems as UiState.Success).data.toImmutableList(),
                                onLikeClick = onLikeClick,
                                onItemClick = onItemClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 43.dp),
                            )

                            HomePopularItemGroup(
                                popularItems = (uiState.popularItems as UiState.Success).data.toImmutableList(),
                                onLikeClick = onLikeClick,
                                onItemClick = onItemClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 43.dp),
                            )

                            HomeMostSearchedItemGroup(
                                searchedItems = (uiState.searchItems as UiState.Success).data.toImmutableList(),
                                onLikeClick = onLikeClick,
                                onItemClick = onItemClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 43.dp, bottom = 40.dp),
                            )
                        }
                    )
                }
            }

            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 6.dp),
            ) { snackBarData ->
                CommonSnackBar(
                    message = snackBarData.visuals.message,
                    backgroundColor = NapzakMarketTheme.colors.black70,
                    textColor = NapzakMarketTheme.colors.white,
                    textStyle = NapzakMarketTheme.typography.bodyMedium14,
                )
            }
        }
    }
}

@Composable
private fun <T> HomeUiStateGroup(
    uiState: UiState<T>,
    success: @Composable (T) -> Unit,
) {
    when (uiState) {
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            success(uiState.data)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val dummyData = UiState.Success(
        mutableListOf<ProductItem>().apply {
            repeat(4) {
                add(
                    ProductItem(
                        productId = 1,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isInterested = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isOwnedByCurrentUser = false,
                        isPriceNegotiable = false,
                    )
                )
            }
        }
    )

    HomeScreen(
        uiState = HomeUiState(
            recommendedItems = dummyData,
            popularItems = dummyData,
            searchItems = dummyData,
            bannerImages = UiState.Success(listOf<HomeBanner>().toImmutableList()),
        ),
        onLikeClick = {_, _ ->},
        onItemClick = {},
    )
}
