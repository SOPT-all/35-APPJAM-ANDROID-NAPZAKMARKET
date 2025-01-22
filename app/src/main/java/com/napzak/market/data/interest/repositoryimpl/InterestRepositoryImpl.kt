package com.napzak.market.data.interest.repositoryimpl

import com.napzak.market.data.interest.datasource.InterestDataSource
import com.napzak.market.domain.interest.repository.InterestRepository
import javax.inject.Inject

class InterestRepositoryImpl @Inject constructor(
    private val dataSource: InterestDataSource,
) : InterestRepository {
    override suspend fun postInterest(productId: Long): Result<Unit> = runCatching {
        dataSource.postInterest(productId)
    }

    override suspend fun deleteInterest(productId: Long): Result<Unit> = runCatching {
        dataSource.deleteInterest(productId)
    }
}