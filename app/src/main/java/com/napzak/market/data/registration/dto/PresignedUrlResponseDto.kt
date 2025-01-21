package com.napzak.market.data.registration.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresignedUrlResponseDto(
    @SerialName("productPresignedUrls")
    val presignedUrls: Map<String, String>,
)
