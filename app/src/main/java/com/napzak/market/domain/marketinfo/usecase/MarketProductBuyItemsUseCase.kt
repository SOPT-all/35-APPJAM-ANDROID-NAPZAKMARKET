package com.napzak.market.domain.marketinfo.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.marketinfo.model.ProductItem
import com.napzak.market.domain.marketinfo.model.ProductListFilter
import com.napzak.market.domain.marketinfo.repository.MarketInfoRepository
import javax.inject.Inject

class MarketProductBuyItemsUseCase @Inject constructor(
    private val marketInfoRepository: MarketInfoRepository,
) {
    suspend operator fun invoke(
        storeId: Long,
        sortType: String,
        genreItems: List<Genre>?,
        isOnSale: Boolean,
    ): Result<List<ProductItem>> {
        val genreIds = if (genreItems?.isNotEmpty() == true) {
            genreItems.map { it.genreId }
        } else {
            null
        }

        return marketInfoRepository.fetchMarketProductBuyItems(
            ProductListFilter(
                storeId = storeId,
                sortOption = sortType,
                genreId = genreIds,
                isOnSale = isOnSale,
                isUnopened = false,
            )
        )
    }
}