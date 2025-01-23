package com.napzak.market.presentation.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.napzak.market.core.designsystem.component.item.NapzakBuyItem
import com.napzak.market.core.designsystem.component.item.NapzakSellItem
import com.napzak.market.domain.product.model.Product

@Composable
fun HomeItemMapper(
    item: Product,
    onLikeClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    with(item) {
        when (tradeType) {
            "BUY" -> {
                NapzakBuyItem(
                    title = productName,
                    genre = genreName,
                    price = price.toString(),
                    imgUrl = photo,
                    isLiked = isInterested,
                    isMyItem = isOwnedByCurrentUser,
                    isOfferPossible = isPriceNegotiable,
                    createdTime = uploadTime,
                    onItemClick = onItemClick,
                    onLikeClick = onLikeClick,
                    modifier = modifier,
                )
            }

            else -> {
                NapzakSellItem(
                    title = productName,
                    genre = genreName,
                    price = price.toString(),
                    imgUrl = photo,
                    isLiked = isInterested,
                    isMyItem = isOwnedByCurrentUser,
                    createdTime = uploadTime,
                    onItemClick = onItemClick,
                    onLikeClick = onLikeClick,
                    modifier = modifier,
                )
            }
        }
    }
}
