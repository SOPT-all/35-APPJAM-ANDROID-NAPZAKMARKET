package com.napzak.market.data.detailpage.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.detailpage.dto.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailPageService {
    @GET("products/{productId}")
    suspend fun getProductDetail(
        @Path("productId") productId: Long
    ): BaseResponse<ProductDetailResponse>
}
