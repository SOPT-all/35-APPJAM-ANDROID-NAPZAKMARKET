package com.napzak.market.data.marketinfo.mapper

import com.napzak.market.data.marketinfo.dto.MarketProductBuyItemsRequest
import com.napzak.market.data.marketinfo.dto.MarketProductBuyItemsResponse
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter

fun ProductListFilter.toMarketProductBuyRequest(): MarketProductBuyItemsRequest =
    MarketProductBuyItemsRequest(
        storeId = this.storeId,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        genreIds = this.genreId,
    )

fun MarketProductBuyItemsResponse.toMarketProductBuyItems(): List<ProductItem> =
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
