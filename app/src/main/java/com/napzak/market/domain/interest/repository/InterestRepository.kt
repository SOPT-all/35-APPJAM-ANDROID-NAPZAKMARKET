package com.napzak.market.domain.interest.repository

interface InterestRepository {
    suspend fun postInterest(productId: Long): Result<Unit>
    suspend fun deleteInterest(productId: Long): Result<Unit>
}