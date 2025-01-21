package com.napzak.market.data.home.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse
import retrofit2.http.GET

interface HomeService {
    @GET("banner/home")
    suspend fun getHomeBanner(): BaseResponse<HomeBannerResponse>

}