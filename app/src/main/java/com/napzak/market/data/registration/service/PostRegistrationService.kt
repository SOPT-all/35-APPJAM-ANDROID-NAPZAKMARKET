package com.napzak.market.data.registration.service

import retrofit2.http.POST

interface PostRegistrationService {

    @POST
    suspend fun postRegistration(
        /* TODO: 게시물 등록 API 연동 */
    )
}
