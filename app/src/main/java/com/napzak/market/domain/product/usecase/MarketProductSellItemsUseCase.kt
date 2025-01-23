package com.napzak.market.domain.product.usecase

import com.napzak.market.domain.product.model.Product
import com.napzak.market.domain.product.model.ProductListFilter
import com.napzak.market.domain.product.repository.MarketProductRepository
import com.napzak.market.domain.genre.model.Genre
import javax.inject.Inject

class MarketProductSellItemsUseCase @Inject constructor(
    private val marketProductRepository: MarketProductRepository,
) {
    suspend operator fun invoke(
        storeId: Long,
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

        return marketProductRepository.fetchMarketProductSellItems(
            ProductListFilter(
                storeId = storeId,
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = isUnopened,
            )
        )
    }
}