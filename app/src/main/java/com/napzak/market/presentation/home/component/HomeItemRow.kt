package com.napzak.market.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.napzak.market.domain.home.model.ProductItem

@Composable
fun HomeItemRow(
    itemList: List<ProductItem>,
    itemWidth: Dp,
    onLikeClick: (Long) -> Unit,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        items(itemList) { item ->
            HomeItemMapper(
                item = item,
                modifier = Modifier.width(itemWidth),
                onItemClick = { onItemClick(item.productId) },
                onLikeClick = { onLikeClick(item.productId) },
            )
        }
    }
}
