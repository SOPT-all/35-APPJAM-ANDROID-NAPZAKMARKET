package com.napzak.market.domain.product.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter
import com.napzak.market.domain.product.repository.ExploreProductRepository
import javax.inject.Inject

class GetProductSellListUseCase @Inject constructor(
    private val exploreProductRepository: ExploreProductRepository,
) {
    suspend operator fun invoke(
        sortType: String,
        genreItems: List<Genre>?,
        isOnSale: Boolean,
        isUnopened: Boolean,
    ): Result<List<Product>> {
        val genreIds = if (genreItems?.isNotEmpty() == true) {
            genreItems.map { it.genreId }
        } else {
            null
        }

        return exploreProductRepository.fetchSellProductItems(
            ProductListFilter(
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = isUnopened,
            )
        )
    }
}

