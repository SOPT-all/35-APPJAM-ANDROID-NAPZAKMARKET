package com.napzak.market.data.mypage.repositoryImpl

import com.napzak.market.data.mypage.datasource.MyPageDataSource
import com.napzak.market.domain.mypage.model.StoreInfo
import com.napzak.market.domain.mypage.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val dataSource: MyPageDataSource
) : MyPageRepository {
    override suspend fun fetchMyPageData(): Result<StoreInfo> = runCatching {
        val response = dataSource.getMyPage()
        StoreInfo(
            storeId = response.data.storeId,
            storeNickname = response.data.storeNickname,
            storePhoto = response.data.storePhoto,
        )
    }
}
