package com.napzak.market.data.detailpage.repositoryimpl

import com.napzak.market.data.detailpage.datasource.DetailPageDataSource
import com.napzak.market.data.detailpage.mapper.toDomain
import com.napzak.market.domain.detailpage.repository.DetailPageRepository
import com.napzak.market.domain.detailpage.model.ProductDetail
import javax.inject.Inject

class DetailPageRepositoryImpl @Inject constructor(
    private val dataSource: DetailPageDataSource
) : DetailPageRepository {
    override suspend fun getProductDetail(productId: Long): Result<ProductDetail> = runCatching {
        val response = dataSource.fetchProductDetail(productId)
        if (response.status == 200) {
            response.data.toDomain()
        } else {
            throw IllegalStateException("Unexpected status code: ${response.status}")
        }
    }
}
