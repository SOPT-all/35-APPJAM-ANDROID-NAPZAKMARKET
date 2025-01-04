package com.napzak.market.data.dummy.service

import com.napzak.market.data.dummy.dto.GetUserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyService {

    @GET("/api/users")
    suspend fun getUserList(@Query("page") page: Int): GetUserListResponse
}