package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.ProductItem
import com.napzak.market.domain.product.model.Product

fun List<ProductItem>.toProductItems(): List<Product> =
    map { productItem ->
        with(productItem) {
            Product(
                productId = productId ?: -1,
                genreName = genreName ?: "",
                productName = productName ?: "",
                photo = photo ?: "",
                price = price ?: 0,
                uploadTime = uploadTime ?: "",
                isInterested = isInterested ?: false,
                tradeType = tradeType ?: "",
                tradeStatus = tradeStatus ?: "",
                isPriceNegotiable = isPriceNegotiable ?: false,
                isOwnedByCurrentUser = isOwnedByCurrentUser ?: false,
            )
        }
    }