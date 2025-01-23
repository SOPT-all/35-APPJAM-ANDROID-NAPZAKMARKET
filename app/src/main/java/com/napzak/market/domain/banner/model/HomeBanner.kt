package com.napzak.market.domain.banner.model

data class HomeBanner(
    val bannerId: Long = 0,
    val bannerPhoto: String = "",
    val bannerUrl: String = "",
    val bannerSequence: Int = 0,
)
