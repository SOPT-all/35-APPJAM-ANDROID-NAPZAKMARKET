package com.napzak.market.data.banner.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.banner.dto.HomeBannerResponse
import retrofit2.http.GET

interface HomeBannerService {

    @GET("banners/home")
    suspend fun getHomeBanner(): BaseResponse<HomeBannerResponse>
}
