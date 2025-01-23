package com.napzak.market.data.registration.repositoryImpl

import com.napzak.market.data.registration.datasource.RegistrationRemoteDataSource
import com.napzak.market.data.registration.mapper.toBuyRegistrationRequestDto
import com.napzak.market.data.registration.mapper.toPresignedUrlMap
import com.napzak.market.data.registration.mapper.toSellRegistrationRequestDto
import com.napzak.market.domain.registration.model.BuyProduct
import com.napzak.market.domain.registration.model.SellProduct
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

    override suspend fun putImageUri(
        presignedUrl: String,
        imageUri: String,
    ): Result<Unit> = runCatching {
        registrationRemoteDataSource.uploadImage(presignedUrl, imageUri)
    }

    override suspend fun postBuyRegistration(buyProduct: BuyProduct): Result<Long> = runCatching {
        registrationRemoteDataSource.postBuyRegistration(buyProduct.toBuyRegistrationRequestDto()).productId
    }

    override suspend fun postSellRegistration(sellProduct: SellProduct): Result<Long> = runCatching {
        registrationRemoteDataSource.postSellRegistration(sellProduct.toSellRegistrationRequestDto()).productId
    }
}
