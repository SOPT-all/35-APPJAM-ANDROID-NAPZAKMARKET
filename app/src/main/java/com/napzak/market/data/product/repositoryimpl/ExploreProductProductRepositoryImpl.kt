package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.product.datasource.ExploreProductDataSource
import com.napzak.market.data.product.mapper.toProductBuyList
import com.napzak.market.data.product.mapper.toProductBuyRequest
import com.napzak.market.data.product.mapper.toProductSellList
import com.napzak.market.data.product.mapper.toProductSellRequest
import com.napzak.market.data.product.mapper.toSearchedProductBuyRequest
import com.napzak.market.data.product.mapper.toSearchedProductSellRequest
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.explore.repository.ExploreProductRepository
import javax.inject.Inject

class ExploreProductProductRepositoryImpl @Inject constructor(
    private val dataSource: ExploreProductDataSource,
) : ExploreProductRepository {
    override suspend fun fetchSellProductItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response = dataSource.getProductSellItems(request.toProductSellRequest())
            response.data.toProductSellList()
        }

    override suspend fun fetchBuyProductItems(request: ProductListFilter): Result<List<ProductItem>> =
        runCatching {
            val response = dataSource.getProductBuyItems(request.toProductBuyRequest())
            response.data.toProductBuyList()
        }

    override suspend fun fetchSearchedSellProductItems(request: ProductListFilterWithSearchTerm): Result<List<ProductItem>> =
        runCatching {
            val response =
                dataSource.getSearchedProductSellItems(request.toSearchedProductSellRequest())
            response.data.toProductSellList()
        }

    override suspend fun fetchSearchedBuyProductItems(request: ProductListFilterWithSearchTerm): Result<List<ProductItem>> =
        runCatching {
            val response =
                dataSource.getSearchedProductBuyItems(request.toSearchedProductBuyRequest())
            response.data.toProductBuyList()
        }
}
