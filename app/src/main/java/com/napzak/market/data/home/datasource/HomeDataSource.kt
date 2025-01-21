package com.napzak.market.data.home.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.home.dto.HomeBannerResponse

interface HomeDataSource {
    suspend fun getHomeBannerList(): BaseResponse<HomeBannerResponse>
}