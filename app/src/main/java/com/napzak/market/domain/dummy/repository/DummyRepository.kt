package com.napzak.market.domain.dummy.repository

import com.napzak.market.domain.dummy.model.DummyUser

interface DummyRepository {
    suspend fun fetchDummyUserList(page: Int): Result<List<DummyUser>>
}