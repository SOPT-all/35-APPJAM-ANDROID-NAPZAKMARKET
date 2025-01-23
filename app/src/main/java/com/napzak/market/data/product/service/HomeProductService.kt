package com.napzak.market.data.product.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductBuyItemsResponse
import com.napzak.market.data.product.dto.response.ProductRecommendItemResponse
import com.napzak.market.data.product.dto.response.ProductSellItemsResponse
import retrofit2.http.GET

interface HomeProductService {

    @GET("products/home/recommend")
    suspend fun getHomeRecommendProduct(): BaseResponse<ProductRecommendItemResponse>

    @GET("products/home/sell")
    suspend fun getHomePopularProduct(): BaseResponse<ProductSellItemsResponse>

    @GET("products/home/buy")
    suspend fun getHomeBuyProduct(): BaseResponse<ProductBuyItemsResponse>
}
