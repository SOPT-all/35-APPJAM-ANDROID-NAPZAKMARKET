package com.napzak.market.domain.marketinfo.repository

import com.napzak.market.domain.marketinfo.model.MarketInfo
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter

interface MarketInfoRepository {
    suspend fun fetchMarketInfo(request: Long): Result<MarketInfo>

    suspend fun fetchMarketProductSellItems(request: ProductListFilter): Result<List<ProductItem>>

    suspend fun fetchMarketProductBuyItems(request: ProductListFilter): Result<List<ProductItem>>
}