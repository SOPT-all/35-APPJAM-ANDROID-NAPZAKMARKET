package com.napzak.market.data.registration.datasource

import android.content.ContentResolver
import android.net.Uri
import com.napzak.market.core.common.util.ContentUriRequestBody
import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
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
    ): String {
        val requestBody = ContentUriRequestBody(contentResolver, Uri.parse(imageUri))
        return imageRegistrationService.putImageUri(presignedUrl, requestBody).data
    }

    suspend fun postRegistration() {
        /* TODO: 상품 등록 API 연동 */
    }
}
