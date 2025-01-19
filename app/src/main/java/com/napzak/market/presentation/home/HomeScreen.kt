package com.napzak.market.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.topbar.NapzakLogoTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.home.component.HomeBannerPager
import com.napzak.market.presentation.home.component.HomeMostSearchedItemGroup
import com.napzak.market.presentation.home.component.HomeRecommendationItemGroup
import com.napzak.market.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        with(viewModel) {
            getBannerImages()
            getRecommendedItems()
            getPopularItems()
            getMostSearchedItems()
        }
    }

    HomeScreen(
        uiState = uiState,
        onLikeClick = {},
        onItemClick = {},
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onLikeClick: (Long) -> Unit,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
    ) {
        LazyColumn(
            modifier = modifier.background(
                color = NapzakMarketTheme.colors.white,
            ),
        ) {
            stickyHeader {
                NapzakLogoTopBar()
            }

            item {
                HomeUiStateGroup(
                    uiState = uiState.bannerImages,
                    success = { uiState ->
                        HomeBannerPager(
                            bannerImages = uiState.toImmutableList(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(360f / 230f),
                        )
                    },
                )

                HomeUiStateGroup(
                    uiState = uiState.recommendedItems,
                    success = { uiState ->
                        HomeRecommendationItemGroup(
                            recommendedItems = uiState.toImmutableList(),
                            onLikeClick = onLikeClick,
                            onItemClick = onItemClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 43.dp),
                        )
                    },
                )

                HomeUiStateGroup(
                    uiState = uiState.popularItems,
                    success = { uiState ->
                        HomeRecommendationItemGroup(
                            recommendedItems = uiState.toImmutableList(),
                            onLikeClick = onLikeClick,
                            onItemClick = onItemClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 43.dp),
                        )
                    },
                )

                HomeUiStateGroup(
                    uiState = uiState.searchItems,
                    success = { uiState ->
                        HomeMostSearchedItemGroup(
                            searchedItems = uiState.toImmutableList(),
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
    HomeScreen(
        uiState = HomeUiState(
            recommendedItems = HomeUiState.dummyData,
            popularItems = HomeUiState.dummyData,
            searchItems = HomeUiState.dummyData,
            bannerImages = HomeUiState.dummyBanner
        ),
        onLikeClick = {},
        onItemClick = {},
    )
}
