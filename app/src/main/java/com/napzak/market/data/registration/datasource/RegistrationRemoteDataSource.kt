package com.napzak.market.data.registration.datasource

import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
import com.napzak.market.data.registration.service.RegistrationService
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    private val registrationService: RegistrationService,
) {

    suspend fun getPresignedUrl(imageTitles: String): PresignedUrlResponseDto =
        registrationService.getPresignedUrl(imageTitles).data
}
