package com.napzak.market.data.product.mapper

import com.napzak.market.data.product.dto.request.SearchedProductSellItemsRequest
import com.napzak.market.domain.product.model.ProductListFilterWithSearchTerm

fun ProductListFilterWithSearchTerm.toSearchedProductSellRequest(): SearchedProductSellItemsRequest =
    SearchedProductSellItemsRequest(
        searchWord = searchWord,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
        nextCursor = "",
    )
