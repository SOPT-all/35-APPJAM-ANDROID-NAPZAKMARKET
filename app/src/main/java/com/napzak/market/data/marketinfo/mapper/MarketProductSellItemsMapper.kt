package com.napzak.market.data.marketinfo.mapper

import com.napzak.market.data.marketinfo.dto.MarketProductSellItemsRequest
import com.napzak.market.data.marketinfo.dto.MarketProductSellItemsResponse
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter

fun ProductListFilter.toMarketProductSellRequest(): MarketProductSellItemsRequest =
    MarketProductSellItemsRequest(
        storeId = this.storeId,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
    )

fun MarketProductSellItemsResponse.toMarketProductSellItems(): List<ProductItem> =
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
