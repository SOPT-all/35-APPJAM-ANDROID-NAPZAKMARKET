package com.napzak.market.domain.banner.repository

import com.napzak.market.domain.banner.model.HomeBanner

interface HomeBannerRepository {
    suspend fun fetchHomeBannerList(): Result<List<HomeBanner>>
}
