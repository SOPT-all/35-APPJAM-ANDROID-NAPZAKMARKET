package com.napzak.market.domain.product.repository

import com.napzak.market.domain.product.model.Product

interface HomeProductRepository {

    suspend fun fetchRecommendProductList(): Result<List<Product>>

    suspend fun fetchPopularProductList(): Result<List<Product>>

    suspend fun fetchBuyProductList(): Result<List<Product>>
}