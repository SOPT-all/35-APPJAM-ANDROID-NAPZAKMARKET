package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm

interface ExploreProductRepository {
    suspend fun fetchSellProductItems(request: ProductListFilter): Result<List<ProductItem>>

    suspend fun fetchBuyProductItems(request: ProductListFilter): Result<List<ProductItem>>

    suspend fun fetchSearchedSellProductItems(request: ProductListFilterWithSearchTerm): Result<List<ProductItem>>

    suspend fun fetchSearchedBuyProductItems(request: ProductListFilterWithSearchTerm): Result<List<ProductItem>>
}