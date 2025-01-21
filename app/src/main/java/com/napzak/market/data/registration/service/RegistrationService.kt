package com.napzak.market.data.registration.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RegistrationService {

    @GET("presigned-url/product")
    suspend fun getPresignedUrl(
        @Query("productImages") imageTitles: String,
    ): BaseResponse<PresignedUrlResponseDto>
}
