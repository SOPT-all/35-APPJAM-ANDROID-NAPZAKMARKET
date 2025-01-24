package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.product.datasource.MarketProductDataSource
import com.napzak.market.data.product.mapper.toMarketProductBuyRequest
import com.napzak.market.data.product.mapper.toMarketProductSellRequest
import com.napzak.market.data.product.mapper.toProductItems
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter
import com.napzak.market.domain.product.repository.MarketProductRepository
import javax.inject.Inject

class MarketProductRepositoryImpl @Inject constructor(
    private val marketProductDataSource: MarketProductDataSource,
) : MarketProductRepository {
    override suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<Product>> =
        runCatching {
            val response =
                marketProductDataSource.getMarketProductSellItems(request.toMarketProductSellRequest())
            response.data.productSellList.toProductItems()
        }

    override suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<Product>> =
        runCatching {
            val response =
                marketProductDataSource.getMarketProductBuyItems(request.toMarketProductBuyRequest())
            response.data.productBuyList.toProductItems()
        }
}