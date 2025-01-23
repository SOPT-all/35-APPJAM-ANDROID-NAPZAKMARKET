package com.napzak.market.data.registration.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.registration.dto.ProductBuyRegistrationRequestDto
import com.napzak.market.data.registration.dto.ProductIdDto
import com.napzak.market.data.registration.dto.ProductSellRegistrationRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PostRegistrationService {

    @POST("products/sell")
    suspend fun postSellRegistration(
        @Body request: ProductSellRegistrationRequestDto
    ): BaseResponse<ProductIdDto>

    @POST("products/buy")
    suspend fun postBuyRegistration(
        @Body request: ProductBuyRegistrationRequestDto
    ): BaseResponse<ProductIdDto>
}
