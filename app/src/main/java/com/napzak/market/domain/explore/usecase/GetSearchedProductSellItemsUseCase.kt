package com.napzak.market.domain.explore.usecase

import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.explore.repository.ExploreRepository
import com.napzak.market.domain.genre.model.Genre
import javax.inject.Inject

class GetSearchedProductSellItemsUseCase @Inject constructor(
    private val exploreRepository: ExploreRepository,
) {
    suspend operator fun invoke(
        searchTerm: String,
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

        return exploreRepository.fetchSearchedSellProductItems(
            ProductListFilterWithSearchTerm(
                searchWord = searchTerm,
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = isUnopened,
            )
        )
    }
}