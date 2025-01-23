package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.request.MarketProductSellItemsRequest
import com.napzak.market.domain.product.model.ProductListFilter

fun ProductListFilter.toMarketProductSellRequest(): MarketProductSellItemsRequest =
    MarketProductSellItemsRequest(
        storeId = this.storeId,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
    )
