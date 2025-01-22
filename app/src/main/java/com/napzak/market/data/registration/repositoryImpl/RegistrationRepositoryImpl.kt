package com.napzak.market.data.registration.repositoryImpl

import com.napzak.market.data.registration.datasource.RegistrationRemoteDataSource
import com.napzak.market.data.registration.mapper.toPresignedUrlMap
import com.napzak.market.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource,
) : RegistrationRepository {

    override suspend fun getPresignedUrl(
        imageTitles: List<String>,
    ): Result<LinkedHashMap<String, String>> = runCatching {
        registrationRemoteDataSource.getPresignedUrl(imageTitles)
            .toPresignedUrlMap()
            .presignedUrls
    }

    override suspend fun postRegistration() {
        /* TODO("Not yet implemented") */
    }

    override suspend fun putImageUri(
        presignedUrl: String,
        imageUri: String,
    ): Result<String> = runCatching {
        registrationRemoteDataSource.uploadImage(presignedUrl, imageUri)
    }
}
