package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.request.ProductBuyItemsRequest
import com.napzak.market.domain.explore.model.ProductListFilter

fun ProductListFilter.toProductBuyRequest(): ProductBuyItemsRequest =
    ProductBuyItemsRequest(
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        genreIds = this.genreId,
        nextCursor = "",
    )
