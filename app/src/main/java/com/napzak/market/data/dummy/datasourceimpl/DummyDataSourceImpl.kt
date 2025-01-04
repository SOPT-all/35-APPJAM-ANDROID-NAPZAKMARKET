package com.napzak.market.data.dummy.datasourceimpl

import com.napzak.market.data.dummy.datasource.DummyDataSource
import com.napzak.market.data.dummy.dto.GetUserListResponse
import com.napzak.market.data.dummy.service.DummyService
import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor(
    private val dummyService: DummyService
) : DummyDataSource {

    override suspend fun getDummyUserList(page: Int): GetUserListResponse {
        return dummyService.getUserList(page)
    }
}