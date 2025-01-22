package com.napzak.market.domain.registration.repository

import com.napzak.market.domain.registration.model.PresignedUrlMap

interface RegistrationRepository {

    suspend fun getPresignedUrl(imageTitles: String): Result<PresignedUrlMap>
}
