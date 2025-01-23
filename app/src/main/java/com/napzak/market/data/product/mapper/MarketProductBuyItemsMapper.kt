package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.request.MarketProductBuyItemsRequest
import com.napzak.market.domain.explore.model.ProductListFilter

fun ProductListFilter.toMarketProductBuyRequest(): MarketProductBuyItemsRequest =
    MarketProductBuyItemsRequest(
        storeId = this.storeId,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        genreIds = this.genreId,
    )
