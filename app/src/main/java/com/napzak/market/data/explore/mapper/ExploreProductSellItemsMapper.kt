package com.napzak.market.data.explore.mapper

import com.napzak.market.data.explore.dto.ProductSellItemsRequest
import com.napzak.market.data.explore.dto.ProductSellItemsResponse
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter

fun ProductListFilter.toProductSellRequest(): ProductSellItemsRequest =
    ProductSellItemsRequest(
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
        nextCursor = "",
    )

fun ProductSellItemsResponse.toProductSellList(): List<ProductItem> =
    productSellList.map { productItem ->
        with(productItem) {
            ProductItem(
                productId = productId,
                genreName = genreName,
                productName = productName,
                photo = photo,
                price = price,
                uploadTime = uploadTime,
                isInterested = isInterested,
                tradeType = tradeType,
                tradeStatus = tradeStatus,
                isOwnedByCurrentUser = isOwnedByCurrentUser,
            )
        }
    }
