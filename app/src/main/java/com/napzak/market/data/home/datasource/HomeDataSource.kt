package com.napzak.market.data.home.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse
import com.napzak.market.data.home.dto.HomeBuyProductResponse
import com.napzak.market.data.home.dto.HomePopularProductResponse
import com.napzak.market.data.home.dto.HomeRecommendProductResponse
import com.napzak.market.data.home.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val service: HomeService
) {

    suspend fun getHomeBanners(): BaseResponse<HomeBannerResponse> =
        service.getHomeBanner()

    suspend fun getRecommendProducts(): BaseResponse<HomeRecommendProductResponse> =
        service.getHomeRecommendProduct()

    suspend fun getPopularProducts(): BaseResponse<HomePopularProductResponse> =
        service.getHomePopularProduct()

    suspend fun getBuyProducts(): BaseResponse<HomeBuyProductResponse> =
        service.getHomeBuyProduct()
}
