package com.napzak.market.data.home.mapper

import com.napzak.market.data.home.dto.HomeBuyProductResponse
import com.napzak.market.domain.home.model.ProductItem

fun HomeBuyProductResponse.toProductItemList(): List<ProductItem> =
    this.productBuyList.map { buyItem ->
        with(buyItem) {
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
                isPriceNegotiable = isPriceNegotiable,
            )
        }
    }