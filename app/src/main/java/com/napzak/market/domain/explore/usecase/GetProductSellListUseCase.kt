package com.napzak.market.domain.explore.usecase

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilter
import com.napzak.market.domain.explore.repository.ExploreRepository
import com.napzak.market.domain.genre.model.Genre
import javax.inject.Inject

class GetProductSellListUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository,
) {
    suspend operator fun invoke(
        sortType: String,
        genreIds: List<Genre>?,
        isOnSale: Boolean,
        isUnopened: Boolean,
    ): Result<List<ProductItem>> {
        val genreIds = if (genreIds?.isNotEmpty() == true) {
            genreIds.map { it.genreId }
        } else {
            null
        }

        return exploreRepository.fetchSellProductItemList(
            ProductListFilter(
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = isUnopened,
            )
        )
    }
}

