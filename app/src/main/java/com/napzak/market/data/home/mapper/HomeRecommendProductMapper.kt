package com.napzak.market.data.home.mapper

import com.napzak.market.data.home.dto.HomeRecommendProductResponse
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem

fun HomeRecommendProductResponse.toProductItemList(): List<ProductItem> = productBuyList.map { buyItem ->
    with(buyItem) {
        ProductItem(
            productId = productId,
            genreName = genreName,
            productName = productName,
            photo = photo,
            price = price,
            uploadTime = uploadTime,
            isLiked = isInterested,
            tradeType = tradeType,
            tradeStatus = tradeStatus,
            isPriceNegotiable = isPriceNegotiable
        )
    }
} + productSellList.map { sellItem ->
    with(sellItem) {
        ProductItem(
            productId = productId,
            genreName = genreName,
            productName = productName,
            photo = photo,
            price = price,
            uploadTime = uploadTime,
            isLiked = isInterested,
            tradeType = tradeType,
            tradeStatus = tradeStatus,
            isPriceNegotiable = false
        )
    }
}
