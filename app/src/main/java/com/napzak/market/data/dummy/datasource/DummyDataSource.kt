package com.napzak.market.data.dummy.datasource

import com.napzak.market.data.dummy.dto.GetUserListResponse

interface DummyDataSource {
    suspend fun getDummyUserList(page: Int): GetUserListResponse
}