package com.napzak.market.data.home.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse
import com.napzak.market.data.home.dto.HomePopularProductResponse
import com.napzak.market.data.home.dto.HomeRecommendProductResponse
import retrofit2.http.GET

interface HomeService {
    @GET("banners/home")
    suspend fun getHomeBanner(): BaseResponse<HomeBannerResponse>

    @GET("products/home/recommend")
    suspend fun getHomeRecommendProduct(): BaseResponse<HomeRecommendProductResponse>

    @GET("products/home/sell")
    suspend fun getHomePopularProduct(): BaseResponse<HomePopularProductResponse>
}
