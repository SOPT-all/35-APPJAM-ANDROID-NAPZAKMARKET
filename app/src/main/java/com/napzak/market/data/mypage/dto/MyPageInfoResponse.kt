package com.napzak.market.data.mypage.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageInfoResponse(
    @SerialName("storeId")
    val storeId: Long,
    @SerialName("storeNickname")
    val storeNickname: String,
    @SerialName("storePhoto")
    val storePhoto: String,
)
