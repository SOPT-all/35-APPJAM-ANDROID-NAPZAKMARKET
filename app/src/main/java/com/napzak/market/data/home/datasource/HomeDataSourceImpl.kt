package com.napzak.market.data.home.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse
import com.napzak.market.data.home.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val service: HomeService
) : HomeDataSource {

    override suspend fun getHomeBannerList(): BaseResponse<HomeBannerResponse> =
        service.getHomeBanner()
}
