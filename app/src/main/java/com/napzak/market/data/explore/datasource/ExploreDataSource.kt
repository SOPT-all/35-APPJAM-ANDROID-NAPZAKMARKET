package com.napzak.market.data.explore.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.explore.dto.ProductBuyItemsRequest
import com.napzak.market.data.explore.dto.ProductBuyItemsResponse
import com.napzak.market.data.explore.dto.ProductSellItemsRequest
import com.napzak.market.data.explore.dto.ProductSellItemsResponse
import com.napzak.market.data.explore.dto.SearchedProductBuyItemsRequest
import com.napzak.market.data.explore.dto.SearchedProductSellItemsRequest
import com.napzak.market.data.explore.service.ExploreService
import javax.inject.Inject

class ExploreDataSource @Inject constructor(
    private val service: ExploreService,
) {
    suspend fun getProductSellItems(request: ProductSellItemsRequest): BaseResponse<ProductSellItemsResponse> =
        service.getProductSellItems(
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
            cursor = "",
        )

    suspend fun getProductBuyItems(request: ProductBuyItemsRequest): BaseResponse<ProductBuyItemsResponse> =
        service.getProductBuyItems(
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
            cursor = "",
        )

    suspend fun getSearchedProductSellItems(request: SearchedProductSellItemsRequest): BaseResponse<ProductSellItemsResponse> =
        service.getSearchedProductSellItems(
            searchWord = request.searchWord,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
            cursor = "",
        )

    suspend fun getSearchedProductBuyItems(request: SearchedProductBuyItemsRequest): BaseResponse<ProductBuyItemsResponse> =
        service.getSearchedProductBuyItems(
            searchWord = request.searchWord,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
            cursor = "",
        )
}
