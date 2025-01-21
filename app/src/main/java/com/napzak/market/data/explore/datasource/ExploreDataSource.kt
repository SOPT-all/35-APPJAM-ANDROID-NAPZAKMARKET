package com.napzak.market.data.explore.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.explore.dto.ProductBuyListRequest
import com.napzak.market.data.explore.dto.ProductBuyListResponse
import com.napzak.market.data.explore.dto.ProductSellListRequest
import com.napzak.market.data.explore.dto.ProductSellListResponse
import com.napzak.market.data.explore.service.ExploreService
import javax.inject.Inject

class ExploreDataSource @Inject constructor(
    private val service: ExploreService,
) {
    suspend fun getProductSellList(request: ProductSellListRequest): BaseResponse<ProductSellListResponse> =
        service.getProductSellList(
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            isUnopened = request.isUnopened,
            genreIds = request.genreIds,
            cursor = "",
        )

    suspend fun getProductBuyList(request: ProductBuyListRequest): BaseResponse<ProductBuyListResponse> =
        service.getProductBuyList(
            sortOption = request.sortOption,
            isOnSale = request.isOnSale,
            genreIds = request.genreIds,
            cursor = "",
        )
}
