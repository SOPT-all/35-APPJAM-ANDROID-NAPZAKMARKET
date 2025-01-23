package com.napzak.market.data.product.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.request.MarketProductBuyItemsRequest
import com.napzak.market.data.product.dto.request.MarketProductSellItemsRequest
import com.napzak.market.data.product.dto.response.ProductBuyItemsResponse
import com.napzak.market.data.product.dto.response.ProductSellItemsResponse
import com.napzak.market.data.product.service.MarketProductService
import javax.inject.Inject

class MarketProductDataSource @Inject constructor(
    private val marketProductService: MarketProductService,
) {
    suspend fun getMarketProductSellItems(request: MarketProductSellItemsRequest): BaseResponse<ProductSellItemsResponse> =
        marketProductService.getMarketProductSellItems(
            storeId = request.storeId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
        )

    suspend fun getMarketProductBuyItems(request: MarketProductBuyItemsRequest): BaseResponse<ProductBuyItemsResponse> =
        marketProductService.getMarketProductBuyItems(
            storeId = request.storeId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
        )
}