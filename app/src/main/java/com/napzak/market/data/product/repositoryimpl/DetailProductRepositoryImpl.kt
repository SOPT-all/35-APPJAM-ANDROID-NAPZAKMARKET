package com.napzak.market.data.product.repositoryimpl

import com.napzak.market.data.product.datasource.DetailProductDataSource
import com.napzak.market.data.product.mapper.toDomain
import com.napzak.market.domain.product.model.ProductDetail
import com.napzak.market.domain.product.repository.DetailProductRepository
import javax.inject.Inject

class DetailProductRepositoryImpl @Inject constructor(
    private val dataSource: DetailProductDataSource
) : DetailProductRepository {
    override suspend fun getProductDetail(productId: Long): Result<ProductDetail> = runCatching {
        val response = dataSource.fetchProductDetail(productId)
        response.data.toDomain()
    }
}
