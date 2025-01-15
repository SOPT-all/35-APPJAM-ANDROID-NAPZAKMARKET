package com.napzak.market.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.topbar.NapzakLogoTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.type.TradeType
import com.napzak.market.presentation.home.component.HomeBannerPager
import com.napzak.market.presentation.home.component.HomeMostSearchedItemGroup
import com.napzak.market.presentation.home.component.HomePopularItemGroup
import com.napzak.market.presentation.home.component.HomeRecommendationItemGroup
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        bannerImages = listOf<Int>(
            R.drawable.img_banner_home1,
            R.drawable.img_banner_home2,
        ).toImmutableList(),
        recommendedItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        popularItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        mostSearchedItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        onLikeClick = {},
        onItemClick = {},
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    @DrawableRes bannerImages: List<Int>,
    recommendedItems: ImmutableList<ProductItem>,
    popularItems: ImmutableList<ProductItem>,
    mostSearchedItems: ImmutableList<ProductItem>,
    onLikeClick: (Int) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
    ) {
        LazyColumn(
            modifier = modifier.background(
                color = NapzakMarketTheme.colors.white
            )
        ) {
            stickyHeader {
                NapzakLogoTopBar()
            }

            item {
                HomeBannerPager(
                    bannerImages = bannerImages,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(360f / 230f),
                )

                HomeRecommendationItemGroup(
                    recommendedItems = recommendedItems,
                    onLikeClick = onLikeClick,
                    onItemClick = onItemClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 43.dp),
                )

                HomePopularItemGroup(
                    popularItems = popularItems,
                    onLikeClick = onLikeClick,
                    onItemClick = onItemClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                )

                HomeMostSearchedItemGroup(
                    searchedItems = mostSearchedItems,
                    onLikeClick = onLikeClick,
                    onItemClick = onItemClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 37.dp, bottom = 40.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        bannerImages = listOf<Int>(
            R.drawable.img_banner_home1,
            R.drawable.img_banner_home2,
        ).toImmutableList(),
        recommendedItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        popularItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        mostSearchedItems = mutableListOf<ProductItem>().apply {
            repeat(4) {
                this.add(
                    ProductItem(
                        productId = it,
                        productName = "딸기 마이멜로디 마스코트 인형",
                        genreName = "산리오",
                        price = 35000,
                        uploadTime = "1시간전",
                        photo = "",
                        isLiked = false,
                        tradeType = TradeType.SELL.name,
                        tradeStatus = "판매중",
                        isPriceNegotiable = false,
                    )
                )
            }
        }.toImmutableList(),
        onLikeClick = {},
        onItemClick = {},
    )
}
