package com.napzak.market.domain.explore.usecase

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.repository.ExploreProductRepository
import com.napzak.market.domain.genre.model.Genre
import javax.inject.Inject

class GetProductSellListUseCase @Inject constructor(
    private val exploreProductRepository: ExploreProductRepository,
) {
    suspend operator fun invoke(
        sortType: String,
        genreItems: List<Genre>?,
        isOnSale: Boolean,
        isUnopened: Boolean,
    ): Result<List<ProductItem>> {
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

