package com.napzak.market.data.detailpage.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.detailpage.service.DetailPageService
import com.napzak.market.data.detailpage.dto.ProductDetailResponse
import javax.inject.Inject

class DetailPageDataSource @Inject constructor(
    private val service: DetailPageService,
) {
    suspend fun fetchProductDetail(productId: Long): BaseResponse<ProductDetailResponse> {
        return service.getProductDetail(productId)
    }
}
