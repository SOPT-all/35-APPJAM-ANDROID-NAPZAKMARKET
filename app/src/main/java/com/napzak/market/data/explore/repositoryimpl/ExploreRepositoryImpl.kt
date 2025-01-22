package com.napzak.market.data.explore.repositoryimpl

import com.napzak.market.data.explore.datasource.ExploreDataSource
import com.napzak.market.data.explore.mapper.toProductBuyList
import com.napzak.market.data.explore.mapper.toProductBuyRequest
import com.napzak.market.data.explore.mapper.toProductSellList
import com.napzak.market.data.explore.mapper.toProductSellRequest
import com.napzak.market.data.explore.mapper.toSearchedProductBuyRequest
import com.napzak.market.data.explore.mapper.toSearchedProductSellRequest
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.explore.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val dataSource: ExploreDataSource,
) : ExploreRepository {
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
