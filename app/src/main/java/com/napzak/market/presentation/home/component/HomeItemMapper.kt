package com.napzak.market.presentation.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.napzak.market.core.designsystem.component.item.NapzakBuyItem
import com.napzak.market.core.designsystem.component.item.NapzakSellItem
import com.napzak.market.domain.home.model.ProductItem

@Composable
fun HomeItemMapper(
    item: ProductItem,
    onLikeClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    with(item) {
        if (item.tradeType == "BUY") {
            NapzakBuyItem(
                title = productName,
                genre = genreName,
                price = price.toString(),
                imgUrl = photo,
                isLiked = isLiked,
                isMyItem = false,
                isOfferPossible = isPriceNegotiable,
                createdTime = uploadTime,
                onItemClick = onItemClick,
                onLikeClick = onLikeClick,
                modifier = modifier,
            )
        } else {
            NapzakSellItem(
                title = productName,
                genre = genreName,
                price = price.toString(),
                imgUrl = photo,
                isLiked = isLiked,
                isMyItem = false,
                createdTime = uploadTime,
                onItemClick = onItemClick,
                onLikeClick = onLikeClick,
                modifier = modifier,
            )
        }
    }
}
