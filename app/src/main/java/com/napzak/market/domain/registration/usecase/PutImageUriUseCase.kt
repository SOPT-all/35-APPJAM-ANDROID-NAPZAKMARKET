package com.napzak.market.domain.registration.usecase

import com.napzak.market.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class PutImageUriUseCase @Inject constructor(
    private val registrationRepository: RegistrationRepository,
) {
    suspend operator fun invoke(
        presignedUrl: String,
        imageUri: String,
    ): Result<String> = registrationRepository.putImageUri(
        presignedUrl = presignedUrl,
        imageUri = imageUri,
    )
}
