package com.napzak.market.data.mypage.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.mypage.dto.MyPageResponse
import retrofit2.http.GET

interface MyPageService {
    @GET("stores/mypage")
    suspend fun getMyPage(): BaseResponse<MyPageResponse>
}
