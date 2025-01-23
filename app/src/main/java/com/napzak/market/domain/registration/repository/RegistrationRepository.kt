package com.napzak.market.domain.registration.repository

import com.napzak.market.domain.registration.model.BuyProduct
import com.napzak.market.domain.registration.model.SellProduct

interface RegistrationRepository {

    suspend fun getPresignedUrl(imageTitles: List<String>): Result<LinkedHashMap<String, String>>

    suspend fun putImageUri(
        presignedUrl: String,
        imageUri: String,
    ): Result<Unit>

    suspend fun postBuyRegistration(buyProduct: BuyProduct): Result<Long>

    suspend fun postSellRegistration(sellProduct: SellProduct): Result<Long>
}
