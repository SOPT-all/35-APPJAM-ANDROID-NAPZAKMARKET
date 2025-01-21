package com.napzak.market.data.home.mapper

import com.napzak.market.data.home.dto.HomeBannerResponse
import com.napzak.market.domain.home.model.HomeBanner

fun HomeBannerResponse.toHomeBannerList(): List<HomeBanner> = bannerList.map { bannerItem ->
    with(bannerItem) {
        HomeBanner(
            bannerId = bannerId,
            bannerPhoto = bannerPhoto,
            bannerUrl = bannerUrl.orEmpty(),
            bannerSequence = bannerSequence,
        )
    }
}