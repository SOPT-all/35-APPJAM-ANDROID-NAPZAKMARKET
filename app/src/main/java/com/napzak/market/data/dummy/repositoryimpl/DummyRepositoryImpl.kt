package com.napzak.market.data.dummy.repositoryimpl

import com.napzak.market.data.dummy.datasource.DummyDataSource
import com.napzak.market.data.dummy.mapper.toDummyUser
import com.napzak.market.domain.dummy.model.DummyUser
import com.napzak.market.domain.dummy.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {

    override suspend fun fetchDummyUserList(page: Int): Result<List<DummyUser>> = runCatching {
        val response = dummyDataSource.getDummyUserList(page)
        response.data?.map { it.toDummyUser() } ?: emptyList()
    }
}