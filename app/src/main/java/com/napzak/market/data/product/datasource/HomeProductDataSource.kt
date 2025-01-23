package com.napzak.market.data.product.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductBuyItemsResponse
import com.napzak.market.data.product.dto.response.ProductRecommendItemResponse
import com.napzak.market.data.product.dto.response.ProductSellItemsResponse
import com.napzak.market.data.product.service.HomeProductService
import javax.inject.Inject

class HomeProductDataSource @Inject constructor(
    private val service: HomeProductService
) {
    suspend fun getRecommendProducts(): BaseResponse<ProductRecommendItemResponse> =
        service.getHomeRecommendProduct()

    suspend fun getPopularProducts(): BaseResponse<ProductSellItemsResponse> =
        service.getHomePopularProduct()

    suspend fun getBuyProducts(): BaseResponse<ProductBuyItemsResponse> =
        service.getHomeBuyProduct()
}
