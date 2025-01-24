package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.request.ProductSellItemsRequest
import com.napzak.market.domain.product.model.ProductListFilter

fun ProductListFilter.toProductSellRequest(): ProductSellItemsRequest =
    ProductSellItemsRequest(
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
        nextCursor = "",
    )
