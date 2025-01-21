package com.napzak.market.data.registration.mapper

import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
import com.napzak.market.domain.registration.model.PresignedUrlMap

fun PresignedUrlResponseDto.toPresignedUrlMap() = PresignedUrlMap(
    presignedUrls = presignedUrls.toMap(),
)
