package com.napzak.market.data.explore.mapper

import com.napzak.market.data.explore.dto.SearchedProductBuyItemsRequest
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm

fun ProductListFilterWithSearchTerm.toSearchedProductBuyRequest(): SearchedProductBuyItemsRequest =
    SearchedProductBuyItemsRequest(
        searchWord = searchWord,
        sortOption = this.sortOption,
        isOnSale = this.isOnSale,
        genreIds = this.genreId,
        nextCursor = "",
    )
