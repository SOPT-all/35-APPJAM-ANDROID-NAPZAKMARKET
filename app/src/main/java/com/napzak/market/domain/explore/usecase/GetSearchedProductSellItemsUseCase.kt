package com.napzak.market.domain.explore.usecase

import com.napzak.market.domain.explore.model.Product
import com.napzak.market.domain.explore.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.explore.repository.ExploreProductRepository
import com.napzak.market.domain.genre.model.Genre
import javax.inject.Inject

class GetSearchedProductSellItemsUseCase @Inject constructor(
    private val exploreProductRepository: ExploreProductRepository,
) {
    suspend operator fun invoke(
        searchTerm: String,
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

        return exploreProductRepository.fetchSearchedSellProductItems(
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