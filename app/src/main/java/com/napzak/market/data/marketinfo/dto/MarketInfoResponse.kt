package com.napzak.market.data.marketinfo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketInfoResponse(
    @SerialName("storeId")
    val storeId: Long,
    @SerialName("storeNickname")
    val storeNickname: String,
    @SerialName("storeDescription")
    val storeDescription: String,
    @SerialName("storePhoto")
    val storePhoto: String,
    @SerialName("storeCover")
    val storeCover: String,
    @SerialName("genrePreferenceList")
    val genrePreferenceList: List<GenrePreference>,
)
