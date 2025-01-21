package com.napzak.market.data.marketinfo.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.MarketInfoResponse
import com.napzak.market.data.marketinfo.dto.ProductBuyItemsRequest
import com.napzak.market.data.marketinfo.dto.ProductBuyItemsResponse
import com.napzak.market.data.marketinfo.dto.ProductSellItemsRequest
import com.napzak.market.data.marketinfo.dto.ProductSellItemsResponse
import com.napzak.market.data.marketinfo.service.MarketInfoService
import javax.inject.Inject

class MarketInfoDataSource @Inject constructor(
    private val service: MarketInfoService,
) {
    suspend fun getMarketInfo(request: Long): BaseResponse<MarketInfoResponse> =
        service.getMarketInfo(storeId = request)

    suspend fun getMarketProductSellItems(request: ProductSellItemsRequest): BaseResponse<ProductSellItemsResponse> =
        service.getMarketProductSellItems(
            storeOwnerId = request.storeOwnerId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
            cursor = "",
        )

    suspend fun getMarketProductBuyItems(request: ProductBuyItemsRequest): BaseResponse<ProductBuyItemsResponse> =
        service.getMarketProductBuyItems(
            storeOwnerId = request.storeOwnerId,
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
            cursor = "",
        )
}
