package com.napzak.market.data.banner.mapper

import com.napzak.market.data.banner.dto.HomeBannerResponse
import com.napzak.market.domain.banner.model.HomeBanner

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