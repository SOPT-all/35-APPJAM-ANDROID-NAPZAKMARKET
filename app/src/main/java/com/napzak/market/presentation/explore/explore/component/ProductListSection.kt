package com.napzak.market.presentation.explore.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.item.NapzakBuyItem
import com.napzak.market.core.designsystem.component.item.NapzakSellItem
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.explore.model.ProductItem

@Composable
fun ProductListSection(
    tradeType: TradeType,
    productList: List<ProductItem>,
    onItemClick: (Long) -> Unit,
    onLikeClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (tradeType == TradeType.SELL) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(productList) { productItem ->
                with(productItem) {
                    NapzakSellItem(
                        title = productName,
                        genre = genreName,
                        price = price.toString(),
                        imgUrl = photo,
                        isLiked = isLiked,
                        isMyItem = isOwnedByCurrentUser,
                        createdTime = uploadTime,
                        onItemClick = { onItemClick(productId) },
                        onLikeClick = { onLikeClick(productId) },
                    )
                }
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(productList) { productItem ->
                with(productItem) {
                    NapzakBuyItem(
                        title = productName,
                        genre = genreName,
                        price = price.toString(),
                        imgUrl = photo,
                        isLiked = isLiked,
                        isMyItem = isOwnedByCurrentUser,
                        isOfferPossible = isPriceNegotiable,
                        createdTime = uploadTime,
                        onItemClick = { onItemClick(productId) },
                        onLikeClick = { onLikeClick(productId) },
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
        tradeType = TradeType.BUY,
        productList = emptyList(),
        onItemClick = { },
        onLikeClick = { },
        modifier = modifier,
    )
}