package com.napzak.market.data.marketinfo.repositoryimpl

import com.napzak.market.data.marketinfo.datasource.MarketInfoDataSource
import com.napzak.market.data.marketinfo.mapper.toMarketInfo
import com.napzak.market.domain.marketinfo.model.MarketInfo
import com.napzak.market.domain.marketinfo.repository.MarketInfoRepository
import javax.inject.Inject

class MarketInfoRepositoryImpl @Inject constructor(
    private val dataSource: MarketInfoDataSource,
) : MarketInfoRepository {
    override suspend fun fetchMarketInfo(request: Long): Result<MarketInfo> =
        runCatching {
            val response = dataSource.getMarketInfo(request = request)
            response.data.toMarketInfo()
        }
}
