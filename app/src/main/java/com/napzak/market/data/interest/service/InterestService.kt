package com.napzak.market.data.interest.service

import com.napzak.market.core.network.NoContentBaseResponse
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface InterestService {

    @POST("interests/{productId}")
    suspend fun postInterest(@Path("productId") productId: Long): NoContentBaseResponse

    @DELETE("interest/{productId}")
    suspend fun deleteInterest(@Path("productId") productId: Long): NoContentBaseResponse
}