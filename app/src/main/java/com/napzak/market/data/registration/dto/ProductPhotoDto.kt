package com.napzak.market.data.registration.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPhotoDto(
    @SerialName("photoUrl")
    val photoUrl: String,
    @SerialName("sequence")
    val sequence: Int,
)
