package com.napzak.market.domain.home.repository

import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem

interface HomeRepository {
    suspend fun fetchHomeBannerList(): Result<List<HomeBanner>>

    suspend fun fetchRecommendProductList(): Result<List<ProductItem>>
}