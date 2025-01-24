package com.napzak.market.domain.product.repository

import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter

interface MarketProductRepository {
    suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<Product>>

    suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<Product>>
}
