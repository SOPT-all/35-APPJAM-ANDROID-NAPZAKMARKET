package com.napzak.market.domain.registration.model

data class PresignedUrlMap(
    val presignedUrls: LinkedHashMap<String, String>,
)
