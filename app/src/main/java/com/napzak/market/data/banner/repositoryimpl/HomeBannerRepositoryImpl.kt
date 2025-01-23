package com.napzak.market.data.banner.repositoryimpl

import com.napzak.market.data.banner.datasource.HomeBannerDataSource
import com.napzak.market.data.banner.mapper.toHomeBannerList
import com.napzak.market.domain.banner.model.HomeBanner
import com.napzak.market.domain.banner.repository.HomeBannerRepository
import javax.inject.Inject

class HomeBannerRepositoryImpl @Inject constructor(
    private val dataSource: HomeBannerDataSource,
) : HomeBannerRepository {
    override suspend fun fetchHomeBannerList(): Result<List<HomeBanner>> = runCatching {
        val response = dataSource.getHomeBanners()
        response.data.toHomeBannerList()
    }
}