package com.napzak.market.data.marketinfo.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.MarketInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarketInfoService {
    @GET("stores/{storeId}")
    suspend fun getMarketInfo(
        @Path("storeId")
        storeId: Long,
    ): BaseResponse<MarketInfoResponse>
}