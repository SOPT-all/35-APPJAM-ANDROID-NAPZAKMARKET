package com.napzak.market.data.mypage.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.mypage.dto.MyPageResponse
import com.napzak.market.data.mypage.service.MyPageService
import javax.inject.Inject

class MyPageDataSource @Inject constructor(
    private val service: MyPageService,
) {
    suspend fun getMyPage(): BaseResponse<MyPageResponse> {
        return service.getMyPage()
    }
}
