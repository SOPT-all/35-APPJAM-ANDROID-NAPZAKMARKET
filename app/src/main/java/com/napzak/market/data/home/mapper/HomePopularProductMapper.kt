package com.napzak.market.data.home.mapper

import com.napzak.market.data.home.dto.HomePopularProductResponse
import com.napzak.market.domain.home.model.ProductItem

fun HomePopularProductResponse.toProductItemList(): List<ProductItem> =
    this.productSellList.map { sellItem ->
        with(sellItem) {
            ProductItem(
                productId = productId,
                genreName = genreName,
                productName = productName,
                photo = photo,
                price = price,
                uploadTime = uploadTime,
                tradeType = tradeType,
                tradeStatus = tradeStatus,
                isInterested = isInterested,
                isOwnedByCurrentUser = isOwnedByCurrentUser,
                isPriceNegotiable = false,
            )
        }
    }