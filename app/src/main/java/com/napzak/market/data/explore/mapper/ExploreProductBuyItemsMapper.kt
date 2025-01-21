package com.napzak.market.data.explore.mapper

import com.napzak.market.data.explore.dto.ProductBuyItemsRequest
import com.napzak.market.data.explore.dto.ProductBuyItemsResponse
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter

fun ProductListFilter.toProductBuyRequest(): ProductBuyItemsRequest =
    ProductBuyItemsRequest(
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        genreIds = this.genreId,
    )

fun ProductBuyItemsResponse.toProductBuyList(): List<ProductItem> =
    productBuyList.map { productItem ->
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
                isPriceNegotiable = isPriceNegotiable,
                isOwnedByCurrentUser = isOwnedByCurrentUser,
            )
        }
    }
