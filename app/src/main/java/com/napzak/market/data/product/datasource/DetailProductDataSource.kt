package com.napzak.market.data.product.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductDetailResponse
import com.napzak.market.data.product.service.DetailProductService
import javax.inject.Inject

class DetailProductDataSource @Inject constructor(
    private val service: DetailProductService,
) {
    suspend fun fetchProductDetail(productId: Long): BaseResponse<ProductDetailResponse> {
        return service.getProductDetail(productId)
    }
}
