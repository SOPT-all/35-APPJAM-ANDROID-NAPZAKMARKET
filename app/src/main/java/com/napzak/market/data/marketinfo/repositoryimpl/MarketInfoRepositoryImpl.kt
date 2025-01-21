package com.napzak.market.data.marketinfo.repositoryimpl

import com.napzak.market.data.marketinfo.datasource.MarketInfoDataSource
import com.napzak.market.data.marketinfo.mapper.toMarketInfo
import com.napzak.market.data.marketinfo.mapper.toMarketProductBuyItems
import com.napzak.market.data.marketinfo.mapper.toMarketProductBuyRequest
import com.napzak.market.data.marketinfo.mapper.toMarketProductSellItems
import com.napzak.market.data.marketinfo.mapper.toMarketProductSellRequest
import com.napzak.market.domain.marketinfo.model.MarketInfo
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter
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

    override suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response = dataSource.getMarketProductSellItems(request.toMarketProductSellRequest())
            response.data.toMarketProductSellItems()
        }

    override suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response = dataSource.getMarketProductBuyItems(request.toMarketProductBuyRequest())
            response.data.toMarketProductBuyItems()
        }
}
