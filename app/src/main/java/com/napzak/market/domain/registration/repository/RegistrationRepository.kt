package com.napzak.market.domain.registration.repository

import com.napzak.market.domain.registration.model.PresignedUrlMap

interface RegistrationRepository {

    suspend fun getPresignedUrlList(imageTitles: String): Result<PresignedUrlMap>
}
