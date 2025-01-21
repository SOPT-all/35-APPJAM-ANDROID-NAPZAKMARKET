package com.napzak.market.data.explore.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.explore.dto.ProductBuyListResponse
import com.napzak.market.data.explore.dto.ProductSellListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreService {
    @GET("products/sell")
    suspend fun getProductSellList(
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("isUnopened")
        isUnopened: Boolean = false,
        @Query("genreId")
        genreIds: List<String>? = null,
        @Query("cursor")
        cursor: String,
    ): BaseResponse<ProductSellListResponse>

    @GET("products/buy")
    suspend fun getProductBuyList(
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("genreId")
        genreIds: List<String>? = null,
        @Query("cursor")
        cursor: String,
    ): BaseResponse<ProductBuyListResponse>
}
