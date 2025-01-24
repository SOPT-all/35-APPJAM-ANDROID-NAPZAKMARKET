package com.napzak.market.domain.product.repository

import com.napzak.market.domain.product.model.ProductDetail

interface DetailProductRepository {
    suspend fun getProductDetail(productId: Long): Result<ProductDetail>
}
