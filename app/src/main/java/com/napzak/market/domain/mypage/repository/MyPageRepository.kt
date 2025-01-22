package com.napzak.market.domain.mypage.repository

import com.napzak.market.domain.mypage.model.StoreInfo

interface MyPageRepository {
    suspend fun fetchMyPageData(): Result<StoreInfo>
}
