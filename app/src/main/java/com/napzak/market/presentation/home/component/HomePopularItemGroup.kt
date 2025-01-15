package com.napzak.market.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.type.TradeType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomePopularItemGroup(
    popularItems: ImmutableList<ProductItem>,
    onLikeClick: (Int) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val itemWidth = remember {
        val metrics = context.resources.displayMetrics
        val width = metrics.widthPixels
        with(density) {
            (width.toFloat() * 150f / 360f).toDp()
        }
    }

    Column(
        modifier = modifier
            .background(color = NapzakMarketTheme.colors.gray50)
            .padding(top = 26.dp, bottom = 40.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(R.drawable.img_home_popular),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .align(Alignment.BottomEnd),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = stringResource(R.string.home_popular_title1),
                    style = NapzakMarketTheme.typography.titleSemi18.copy(
                        color = NapzakMarketTheme.colors.gray900,
                    ),
                )

                Text(
                    text = stringResource(R.string.home_popular_body1),
                    style = NapzakMarketTheme.typography.bodySemi14.copy(
                        color = NapzakMarketTheme.colors.gray500,
                    ),
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }

        popularItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                rowItems.forEach { item ->
                    HomeItemMapper(
                        item = item,
                        onItemClick = { onItemClick(item.productId) },
                        onLikeClick = { onLikeClick(item.productId) },
                        modifier = Modifier.width(itemWidth),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePopularGroupPreview() {
    NapzakMarketTheme {
        HomePopularItemGroup(
            modifier = Modifier.fillMaxWidth(),
            onLikeClick = {},
            onItemClick = {},
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
            }.toImmutableList()
        )
    }
}
