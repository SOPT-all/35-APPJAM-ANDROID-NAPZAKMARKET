package com.napzak.market.data.genre.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.GenreRegisterRequest
import com.napzak.market.data.genre.dto.GenreRegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GenreManagementService {

    @POST("stores/register")
    suspend fun postGenre(
        @Body request: GenreRegisterRequest,
    ): BaseResponse<GenreRegisterResponse>
}