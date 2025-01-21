package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductRequest

interface ExploreRepository {
    suspend fun fetchProductItemList(request: ProductRequest): Result<List<ProductItem>>
}