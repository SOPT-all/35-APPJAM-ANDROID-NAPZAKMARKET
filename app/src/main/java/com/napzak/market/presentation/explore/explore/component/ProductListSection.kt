package com.napzak.market.presentation.explore.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.item.NapzakBuyItem
import com.napzak.market.core.designsystem.component.item.NapzakSellItem
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.explore.model.Product

@Composable
fun ProductListSection(
    gridState: LazyGridState,
    tradeType: TradeType,
    productList: List<Product>,
    onItemClick: (Long) -> Unit,
    onLikeClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (tradeType == TradeType.SELL) {
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
    ProductListSection(
        gridState = LazyGridState(),
        tradeType = TradeType.BUY,
        productList = emptyList(),
        onItemClick = { },
        onLikeClick = { _, _ -> },
        modifier = modifier,
    )
}