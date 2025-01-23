package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.product.datasource.ExploreProductDataSource
import com.napzak.market.data.product.mapper.toProductBuyRequest
import com.napzak.market.data.product.mapper.toProductItems
import com.napzak.market.data.product.mapper.toProductSellRequest
import com.napzak.market.data.product.mapper.toSearchedProductBuyRequest
import com.napzak.market.data.product.mapper.toSearchedProductSellRequest
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter
import com.napzak.market.domain.product.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.product.repository.ExploreProductRepository
import javax.inject.Inject

class ExploreProductRepositoryImpl @Inject constructor(
    private val dataSource: ExploreProductDataSource,
) : ExploreProductRepository {
    override suspend fun fetchSellProductItems(request: ProductListFilter): Result<List<Product>> =
        runCatching {
            val response = dataSource.getProductSellItems(request.toProductSellRequest())
            response.data.productSellList.toProductItems()
        }

    override suspend fun fetchBuyProductItems(request: ProductListFilter): Result<List<Product>> =
        runCatching {
            val response = dataSource.getProductBuyItems(request.toProductBuyRequest())
            response.data.productBuyList.toProductItems()
        }

    override suspend fun fetchSearchedSellProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>> =
        runCatching {
            val response =
                dataSource.getSearchedProductSellItems(request.toSearchedProductSellRequest())
            response.data.productSellList.toProductItems()
        }

    override suspend fun fetchSearchedBuyProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>> =
        runCatching {
            val response =
                dataSource.getSearchedProductBuyItems(request.toSearchedProductBuyRequest())
            response.data.productBuyList.toProductItems()
        }
}
