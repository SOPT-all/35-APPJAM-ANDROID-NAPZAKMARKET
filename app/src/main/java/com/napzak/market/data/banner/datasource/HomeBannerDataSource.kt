package com.napzak.market.data.banner.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.banner.dto.HomeBannerResponse
import com.napzak.market.data.banner.service.HomeBannerService
import javax.inject.Inject

class HomeBannerDataSource @Inject constructor(
    private val service: HomeBannerService
) {

    suspend fun getHomeBanners(): BaseResponse<HomeBannerResponse> =
        service.getHomeBanner()
}
