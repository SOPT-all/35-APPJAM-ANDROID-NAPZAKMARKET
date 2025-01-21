package com.napzak.market.data.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeBannerResponse(
    @SerialName("bannerList")
    val bannerList: List<BannerItem>
)

@Serializable
data class BannerItem(
    @SerialName("bannerId")
    val bannerId: Long,
    @SerialName("bannerPhoto")
    val bannerPhoto: String,
    @SerialName("bannerUrl")
    val bannerUrl: String?,
    @SerialName("bannerSequence")
    val bannerSequence: Int,
)

