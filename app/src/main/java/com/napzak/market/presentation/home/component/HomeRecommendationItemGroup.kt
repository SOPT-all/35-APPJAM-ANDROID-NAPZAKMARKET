package com.napzak.market.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRecommendationItemGroup(
    recommendedItems: ImmutableList<ProductItem>,
    onLikeClick: (Long) -> Unit,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context by rememberUpdatedState( LocalContext.current )
    val density by rememberUpdatedState( LocalDensity.current )

    val itemWidth = remember {
        val metrics = context.resources.displayMetrics
        val width = metrics.widthPixels
        with(density) {
            (width.toFloat() * 150f / 360f).toDp()
        }
    }

    val textModifier = Modifier.padding(top = 2.dp)

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.home_recommendation_title1),
                style = NapzakMarketTheme.typography.titleSemi18.copy(
                    color = NapzakMarketTheme.colors.gray900
                ),
                modifier = textModifier,
            )

            Text(
                text = stringResource(R.string.home_recommendation_title2),
                style = NapzakMarketTheme.typography.titleSemi18.copy(
                    color = NapzakMarketTheme.colors.purple30
                ),
                modifier = textModifier.padding(start = 4.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.img_home_recommend),
                contentDescription = null,
                modifier = Modifier.padding(start = 6.dp),
            )
        }

        Text(
            text = stringResource(R.string.home_recommendation_body1),
            style = NapzakMarketTheme.typography.bodySemi14.copy(
                color = NapzakMarketTheme.colors.gray500,
            ),
            modifier = textModifier
                .padding(top = 4.dp)
                .padding(horizontal = 20.dp),
        )

        HomeItemRow(
            modifier = Modifier.padding(top = 16.dp),
            itemList = recommendedItems,
            itemWidth = itemWidth,
            onItemClick = onItemClick,
            onLikeClick = onLikeClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeRecommendationGroupPreview() {
    NapzakMarketTheme {
        HomeRecommendationItemGroup(
            onLikeClick = {},
            onItemClick = {},
            recommendedItems = mutableListOf<ProductItem>().apply {
                repeat(4) {
                    this.add(
                        ProductItem(
                            productId = it.toLong(),
                            productName = "딸기 마이멜로디 마스코트 인형",
                            genreName = "산리오",
                            price = 35000,
                            uploadTime = "1시간전",
                            photo = "",
                            isLiked = false,
                            tradeType = "구해요",
                            tradeStatus = "판매중",
                            isPriceNegotiable = false,
                        )
                    )
                }
            }.toImmutableList()
        )
    }
}
