package com.napzak.market.presentation.explore.component

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
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.type.TradeType

@Composable
fun ProductListSection(
    tradeType: String,
    productList: List<ProductItem>,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (tradeType == TradeType.SELL.name) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(productList) { productItem ->
                NapzakSellItem(
                    title = productItem.productName,
                    genre = productItem.genreName,
                    price = productItem.price.toString(),
                    imgUrl = productItem.photo,
                    isLiked = productItem.isLiked,
                    isMyItem = false,
                    createdTime = productItem.uploadTime,
                    onItemClick = { onItemClick(productItem.productId) },
                    onLikeClick = { onLikeClick(productItem.productId) },
                )
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
                NapzakBuyItem(
                    title = productItem.productName,
                    genre = productItem.genreName,
                    price = productItem.price.toString(),
                    imgUrl = productItem.photo,
                    isLiked = productItem.isLiked,
                    isMyItem = false,
                    isOfferPossible = productItem.isPriceNegotiable,
                    createdTime = productItem.uploadTime,
                    onItemClick = { onItemClick(productItem.productId) },
                    onLikeClick = { onLikeClick(productItem.productId) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListSectionPreview(modifier: Modifier = Modifier) {
    ProductListSection(
        tradeType = TradeType.BUY.name,
        productList = listOf(
            ProductItem(
                productId = 201,
                genreName = "짱구",
                productName = "피규어",
                photo = "",
                price = 120000,
                uploadTime = "3일",
                isLiked = true,
                tradeType = "SELL",
                tradeStatus = "BEFORE_TRADE",
            ),
            ProductItem(
                productId = 201,
                genreName = "짱구",
                productName = "피규어",
                photo = "",
                price = 120000,
                uploadTime = "3일",
                isLiked = false,
                tradeType = "SELL",
                tradeStatus = "BEFORE_TRADE",
            ),
        ),
        onItemClick = { },
        onLikeClick = { },
        modifier = modifier,
    )
}