package com.napzak.market.domain.mypage.repository

import com.napzak.market.domain.mypage.model.MyPageData

interface MyPageRepository {
    suspend fun fetchMyPageData(): Result<MyPageData>
}
