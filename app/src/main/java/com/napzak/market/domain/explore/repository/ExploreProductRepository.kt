package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.explore.model.Product
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm

interface ExploreProductRepository {
    suspend fun fetchSellProductItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchBuyProductItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchSearchedSellProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>>

    suspend fun fetchSearchedBuyProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>>
}