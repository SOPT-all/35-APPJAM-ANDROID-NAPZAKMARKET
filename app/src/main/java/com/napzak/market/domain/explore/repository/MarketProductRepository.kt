package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.explore.model.Product
import com.napzak.market.domain.explore.model.ProductListFilter

interface MarketProductRepository {
    suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<Product>>
}
