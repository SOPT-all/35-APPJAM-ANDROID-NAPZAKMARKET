package com.napzak.market.domain.detailpage.repository

import com.napzak.market.domain.detailpage.model.ProductDetail

interface DetailPageRepository {
    suspend fun getProductDetail(productId: Long): Result<ProductDetail>
}
