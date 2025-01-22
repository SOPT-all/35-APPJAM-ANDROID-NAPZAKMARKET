package com.napzak.market.data.marketinfo.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.MarketProductBuyItemsResponse
import com.napzak.market.data.marketinfo.dto.MarketProductSellItemsResponse
import com.napzak.market.data.marketinfo.dto.MarketInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketInfoService {
    @GET("stores/{storeId}")
    suspend fun getMarketInfo(
        @Path("storeId")
        storeId: Long,
    ): BaseResponse<MarketInfoResponse>

    @GET("products/sell/stores/{storeOwnerId}")
    suspend fun getMarketProductSellItems(
        @Path("storeOwnerId")
        storeOwnerId: Long,
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("isUnopened")
        isUnopened: Boolean = false,
        @Query("genreId")
        genreIds: List<Long>? = null,
        @Query("cursor")
        cursor: String = "",
    ): BaseResponse<MarketProductSellItemsResponse>

    @GET("products/buy/stores/{storeOwnerId}")
    suspend fun getMarketProductBuyItems(
        @Path("storeOwnerId")
        storeOwnerId: Long,
        @Query("sortOption")
        sortOption: String = "RECENT",
        @Query("isOnSale")
        isOnSale: Boolean = false,
        @Query("isUnopened")
        isUnopened: Boolean = false,
        @Query("genreId")
        genreIds: List<Long>? = null,
        @Query("cursor")
        cursor: String = "",
    ): BaseResponse<MarketProductBuyItemsResponse>
}