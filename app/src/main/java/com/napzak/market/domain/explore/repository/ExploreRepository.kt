package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter

interface ExploreRepository {
    suspend fun fetchProductItemList(request: ProductListFilter): Result<List<ProductItem>>
}