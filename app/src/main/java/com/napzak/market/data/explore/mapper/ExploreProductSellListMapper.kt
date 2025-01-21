package com.napzak.market.data.explore.mapper

import com.napzak.market.data.explore.dto.ProductSellListRequest
import com.napzak.market.data.explore.dto.ProductSellListResponse
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter

fun ProductListFilter.toProductSellRequest(): ProductSellListRequest =
    ProductSellListRequest(
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
    )

fun ProductSellListResponse.toProductSellList(): List<ProductItem> =
    productSellList.map { productItem ->
        with(productItem) {
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
                isOwnedByCurrentUser = isOwnedByCurrentUser,
            )
        }
    }