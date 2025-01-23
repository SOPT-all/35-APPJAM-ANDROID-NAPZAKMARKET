package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.ProductItem
import com.napzak.market.domain.product.model.Product

fun List<ProductItem>.toProductItems(): List<Product> =
    map { productItem ->
        with(productItem) {
            Product(
                productId = productId,
                genreName = genreName,
                productName = productName,
                photo = photo,
                price = price,
                uploadTime = uploadTime,
                isInterested = isInterested,
                tradeType = tradeType,
                tradeStatus = tradeStatus,
                isPriceNegotiable = isPriceNegotiable,
                isOwnedByCurrentUser = isOwnedByCurrentUser,
            )
        }
    }