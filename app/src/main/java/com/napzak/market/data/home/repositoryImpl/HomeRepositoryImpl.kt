package com.napzak.market.data.home.repositoryImpl

import com.napzak.market.data.home.datasource.HomeDataSource
import com.napzak.market.data.home.mapper.toHomeBannerList
import com.napzak.market.data.home.mapper.toProductItemList
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.model.ProductItem
import com.napzak.market.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun fetchHomeBannerList(): Result<List<HomeBanner>> = runCatching {
        val response = dataSource.getHomeBannerList()
        response.data.toHomeBannerList()
    }

    override suspend fun fetchRecommendProductList(): Result<List<ProductItem>> = runCatching {
        val response = dataSource.getRecommendProductList()
        response.data.toProductItemList()
    }

    override suspend fun fetchPopularProductList(): Result<List<ProductItem>> = runCatching {
        val response = dataSource.getPopularProductList()
        response.data.toProductItemList()

    }
}