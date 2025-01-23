package com.napzak.market.data.product.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.MarketProductBuyItemsRequest
import com.napzak.market.data.marketinfo.dto.MarketProductBuyItemsResponse
import com.napzak.market.data.marketinfo.dto.MarketProductSellItemsRequest
import com.napzak.market.data.marketinfo.dto.MarketProductSellItemsResponse
import com.napzak.market.data.product.service.MarketProductService
import javax.inject.Inject

class MarketProductDataSource @Inject constructor(
    private val marketProductService: MarketProductService,
) {
    suspend fun getMarketProductSellItems(request: MarketProductSellItemsRequest): BaseResponse<MarketProductSellItemsResponse> =
        marketProductService.getMarketProductSellItems(
            storeId = request.storeId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
        )

    suspend fun getMarketProductBuyItems(request: MarketProductBuyItemsRequest): BaseResponse<MarketProductBuyItemsResponse> =
        marketProductService.getMarketProductBuyItems(
            storeId = request.storeId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
        )
}