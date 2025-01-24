package com.napzak.market.domain.product.repository

import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter
import com.napzak.market.domain.product.model.ProductListFilterWithSearchTerm

interface ExploreProductRepository {
    suspend fun fetchSellProductItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchBuyProductItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchSearchedSellProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>>

    suspend fun fetchSearchedBuyProductItems(request: ProductListFilterWithSearchTerm): Result<List<Product>>
}