package com.napzak.market.domain.explore.repository

import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter

interface MarketProductRepository {
    suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<ProductItem>>

    suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<ProductItem>>
}
