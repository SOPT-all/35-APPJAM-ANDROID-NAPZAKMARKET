package com.napzak.market.data.registration.datasource

import android.content.ContentResolver
import android.net.Uri
import com.napzak.market.core.common.util.ContentUriRequestBody
import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
import com.napzak.market.data.registration.dto.ProductBuyRegistrationRequestDto
import com.napzak.market.data.registration.dto.ProductIdDto
import com.napzak.market.data.registration.dto.ProductSellRegistrationRequestDto
import com.napzak.market.data.registration.service.ImageRegistrationService
import com.napzak.market.data.registration.service.PostRegistrationService
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    private val contentResolver: ContentResolver,
    private val imageRegistrationService: ImageRegistrationService,
    private val postRegistrationService: PostRegistrationService,
) {

    suspend fun getPresignedUrl(imageTitles: List<String>): PresignedUrlResponseDto =
        imageRegistrationService.getPresignedUrl(imageTitles).data

    suspend fun uploadImage(
        presignedUrl: String,
        imageUri: String,
    ) = imageRegistrationService.putImageUri(presignedUrl, ContentUriRequestBody(contentResolver, Uri.parse(imageUri)))

    suspend fun postBuyRegistration(
        productBuyRegistrationRequestDto: ProductBuyRegistrationRequestDto,
    ): ProductIdDto = postRegistrationService
        .postBuyRegistration(productBuyRegistrationRequestDto).data

    suspend fun postSellRegistration(
        productSellRegistrationRequestDto: ProductSellRegistrationRequestDto,
    ): ProductIdDto = postRegistrationService
        .postSellRegistration(productSellRegistrationRequestDto).data
}
