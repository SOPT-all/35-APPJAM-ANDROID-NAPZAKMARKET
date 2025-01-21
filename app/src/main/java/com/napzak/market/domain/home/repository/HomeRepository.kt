package com.napzak.market.domain.home.repository

import com.napzak.market.domain.home.model.HomeBanner

interface HomeRepository {
    suspend fun fetchHomeBannerList(): Result<List<HomeBanner>>
}