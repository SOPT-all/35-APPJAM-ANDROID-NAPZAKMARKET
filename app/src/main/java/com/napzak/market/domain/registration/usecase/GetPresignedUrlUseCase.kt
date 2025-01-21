package com.napzak.market.domain.registration.usecase

import com.napzak.market.domain.registration.model.PresignedUrlMap
import com.napzak.market.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class GetPresignedUrlUseCase @Inject constructor(
    private val registrationRepository: RegistrationRepository,
) {
    suspend operator fun invoke(
        imageTitles: List<String>,
    ): Result<PresignedUrlMap> = registrationRepository.getPresignedUrlList(
        imageTitles = List(imageTitles.size) { index ->
            "$IMAGE_TITLE_PREFIX${index + 1}.jpg"
        }.joinToString("&")
    )
}

private const val IMAGE_TITLE_PREFIX = "image_"
