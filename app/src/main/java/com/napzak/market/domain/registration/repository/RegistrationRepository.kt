package com.napzak.market.domain.registration.repository

interface RegistrationRepository {

    suspend fun getPresignedUrl(imageTitles: List<String>): Result<LinkedHashMap<String, String>>

    suspend fun putImageUri(
        presignedUrl: String,
        imageUri: String,
    ): Result<Unit>

    suspend fun postRegistration(
        /* TODO: 게시물 등록 API 연동 */
    )
}
