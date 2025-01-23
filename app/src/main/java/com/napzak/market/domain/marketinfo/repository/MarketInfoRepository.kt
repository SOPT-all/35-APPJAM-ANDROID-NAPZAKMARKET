package com.napzak.market.domain.marketinfo.repository

import com.napzak.market.domain.marketinfo.model.MarketInfo

interface MarketInfoRepository {
    suspend fun fetchMarketInfo(request: Long): Result<MarketInfo>
}
