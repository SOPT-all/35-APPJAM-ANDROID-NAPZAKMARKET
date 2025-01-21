package com.napzak.market.data.explore.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.explore.dto.ProductBuyItemsResponse
import com.napzak.market.data.explore.dto.ProductSellItemsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreService {
    @GET("products/sell")
    suspend fun getProductSellItems(
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("isUnopened")
        isUnopened: Boolean = false,
        @Query("genreId")
        genreIds: List<Long>? = null,
        @Query("cursor")
        cursor: String,
    ): BaseResponse<ProductSellItemsResponse>

    @GET("products/buy")
    suspend fun getProductBuyItems(
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("genreId")
        genreIds: List<Long>? = null,
        @Query("cursor")
        cursor: String,
    ): BaseResponse<ProductBuyItemsResponse>
}
