package com.napzak.market.data.product.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailProductService {
    @GET("products/{productId}")
    suspend fun getProductDetail(
        @Path("productId") productId: Long,
    ): BaseResponse<ProductDetailResponse>
}
