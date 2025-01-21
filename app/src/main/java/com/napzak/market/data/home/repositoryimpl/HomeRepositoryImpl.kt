package com.napzak.market.data.home.repositoryimpl

import com.napzak.market.data.home.datasource.HomeDataSource
import com.napzak.market.data.home.mapper.toHomeBannerList
import com.napzak.market.domain.home.model.HomeBanner
import com.napzak.market.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun fetchHomeBannerList(): Result<List<HomeBanner>> = runCatching {
        val response = dataSource.getHomeBannerList()

        response.data.toHomeBannerList()
    }
}