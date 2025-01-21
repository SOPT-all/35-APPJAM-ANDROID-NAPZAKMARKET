package com.napzak.market.data.registration.repositoryImpl

import com.napzak.market.data.registration.datasource.RegistrationRemoteDataSource
import com.napzak.market.data.registration.mapper.toPresignedUrlMap
import com.napzak.market.domain.registration.model.PresignedUrlMap
import com.napzak.market.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource,
) : RegistrationRepository {

    override suspend fun getPresignedUrlList(
        imageTitles: String,
    ): Result<PresignedUrlMap> = runCatching {
        registrationRemoteDataSource.getPresignedUrl(imageTitles).toPresignedUrlMap()
    }
}