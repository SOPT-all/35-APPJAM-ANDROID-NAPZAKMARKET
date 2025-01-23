package com.napzak.market.data.marketinfo.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.MarketInfoResponse
import com.napzak.market.data.marketinfo.service.MarketInfoService
import javax.inject.Inject

class MarketInfoDataSource @Inject constructor(
    private val service: MarketInfoService,
) {
    suspend fun getMarketInfo(request: Long): BaseResponse<MarketInfoResponse> =
        service.getMarketInfo(storeId = request)
}
