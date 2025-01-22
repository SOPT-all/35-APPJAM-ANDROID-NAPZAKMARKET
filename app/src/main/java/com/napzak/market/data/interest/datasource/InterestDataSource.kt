package com.napzak.market.data.interest.datasource

import com.napzak.market.core.network.NoContentBaseResponse
import com.napzak.market.data.interest.service.InterestService
import javax.inject.Inject

class InterestDataSource @Inject constructor(
    private val interestService: InterestService,
) {
    suspend fun postInterest(productId: Long): NoContentBaseResponse =
        interestService.postInterest(productId)

    suspend fun deleteInterest(productId: Long): NoContentBaseResponse =
        interestService.deleteInterest(productId)
}