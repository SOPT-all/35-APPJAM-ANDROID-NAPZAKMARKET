package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.marketinfo.mapper.toMarketProductBuyItems
import com.napzak.market.data.marketinfo.mapper.toMarketProductBuyRequest
import com.napzak.market.data.marketinfo.mapper.toMarketProductSellItems
import com.napzak.market.data.marketinfo.mapper.toMarketProductSellRequest
import com.napzak.market.data.product.datasource.MarketProductDataSource
import com.napzak.market.domain.explore.repository.MarketProductRepository
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter
import javax.inject.Inject

class MarketProductRepositoryImpl @Inject constructor(
    private val marketProductDataSource: MarketProductDataSource,
) : MarketProductRepository {
    override suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response =
                marketProductDataSource.getMarketProductSellItems(request.toMarketProductSellRequest())
            response.data.toMarketProductSellItems()
        }

    override suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response =
                marketProductDataSource.getMarketProductBuyItems(request.toMarketProductBuyRequest())
            response.data.toMarketProductBuyItems()
        }
}