package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.product.datasource.HomeProductDataSource
import com.napzak.market.data.product.mapper.toProductItems
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.repository.HomeProductRepository
import javax.inject.Inject

class HomeProductRepositoryImpl @Inject constructor(
    private val dataSource: HomeProductDataSource,
) : HomeProductRepository {

    override suspend fun fetchRecommendProductList(): Result<List<Product>> = runCatching {
        val response = dataSource.getRecommendProducts()
        response.data.productRecommendList.toProductItems()
    }

    override suspend fun fetchPopularProductList(): Result<List<Product>> = runCatching {
        val response = dataSource.getPopularProducts()
        response.data.productSellList.toProductItems()
    }

    override suspend fun fetchBuyProductList(): Result<List<Product>> = runCatching {
        val response = dataSource.getBuyProducts()
        response.data.productBuyList.toProductItems()
    }
}