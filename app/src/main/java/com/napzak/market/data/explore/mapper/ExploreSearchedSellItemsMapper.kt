package com.napzak.market.data.explore.mapper

import com.napzak.market.data.explore.dto.request.SearchedProductSellItemsRequest
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm

fun ProductListFilterWithSearchTerm.toSearchedProductSellRequest(): SearchedProductSellItemsRequest =
    SearchedProductSellItemsRequest(
        searchWord = searchWord,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        isUnopened = this.isUnopened,
        genreIds = this.genreId,
        nextCursor = "",
    )
