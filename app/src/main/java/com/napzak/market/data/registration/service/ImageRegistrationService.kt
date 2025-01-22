package com.napzak.market.data.registration.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.registration.dto.PresignedUrlResponseDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Url

interface ImageRegistrationService {

    @GET("presigned-url/product")
    suspend fun getPresignedUrl(
        @Query("productImages") imageTitles: List<String>,
    ): BaseResponse<PresignedUrlResponseDto>

    @PUT
    suspend fun putImageUri(
        @Url presignedUrl: String,
        @Body requestBody: RequestBody,
    ): BaseResponse<String>
}
