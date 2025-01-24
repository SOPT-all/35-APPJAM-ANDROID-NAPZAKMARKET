package com.napzak.market.presentation.marketinfo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.item.NapzakBuyItem
import com.napzak.market.core.designsystem.component.item.NapzakSellItem
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.MarketTab
import com.napzak.market.core.type.SortType
import com.napzak.market.domain.product.model.Product

@Composable
fun MarketProductListSection(
    gridState: LazyGridState,
    tradeType: MarketTab,
    sortType: SortType,
    productList: List<Product>,
    onSortButtonClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    onLikeClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item(span = { GridItemSpan(2) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
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
                        productList.size,
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
        }

        if (tradeType == MarketTab.SELL) {
            items(productList) { productItem ->
                with(productItem) {
                    NapzakSellItem(
                        title = productName,
                        genre = genreName,
                        price = price.toString(),
                        imgUrl = photo,
                        isLiked = isInterested,
                        isMyItem = isOwnedByCurrentUser,
                        createdTime = uploadTime,
                        onItemClick = { onItemClick(productId) },
                        onLikeClick = { onLikeClick(productId, isInterested) },
                    )
                }
            }
        } else {
            items(productList) { productItem ->
                with(productItem) {
                    NapzakBuyItem(
                        title = productName,
                        genre = genreName,
                        price = price.toString(),
                        imgUrl = photo,
                        isLiked = isInterested,
                        isMyItem = isOwnedByCurrentUser,
                        isOfferPossible = isPriceNegotiable,
                        createdTime = uploadTime,
                        onItemClick = { onItemClick(productId) },
                        onLikeClick = { onLikeClick(productId, isInterested) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListSectionPreview(modifier: Modifier = Modifier) {
    MarketProductListSection(
        gridState = LazyGridState(),
        tradeType = MarketTab.BUY,
        sortType = SortType.RECENT,
        productList = emptyList(),
        onSortButtonClick = {},
        onItemClick = { },
        onLikeClick = { _, _ -> },
        modifier = modifier,
    )
}