package com.napzak.market.domain.product.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilterWithSearchTerm
import com.napzak.market.domain.product.repository.ExploreProductRepository
import javax.inject.Inject

class GetSearchedProductBuyItemsUseCase @Inject constructor(
    private val exploreProductRepository: ExploreProductRepository,
) {
    suspend operator fun invoke(
        searchTerm: String,
        sortType: String,
        genreItems: List<Genre>?,
        isOnSale: Boolean,
    ): Result<List<Product>> {
        val genreIds = if (genreItems?.isNotEmpty() == true) {
            genreItems.map { it.genreId }
        } else {
            null
        }

        return exploreProductRepository.fetchSearchedBuyProductItems(
            ProductListFilterWithSearchTerm(
                searchWord = searchTerm,
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = false,
            )
        )
    }
}