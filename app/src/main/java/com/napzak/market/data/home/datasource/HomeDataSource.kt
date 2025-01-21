package com.napzak.market.data.home.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse
import com.napzak.market.data.home.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val service: HomeService
) {
    suspend fun getHomeBannerList(): BaseResponse<HomeBannerResponse> =
        service.getHomeBanner()
}
