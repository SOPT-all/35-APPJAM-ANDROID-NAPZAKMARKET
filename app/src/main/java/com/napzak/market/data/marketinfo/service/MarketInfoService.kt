package com.napzak.market.data.marketinfo.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.marketinfo.dto.ProductBuyItemsResponse
import com.napzak.market.data.marketinfo.dto.ProductSellItemsResponse
import com.napzak.market.data.marketinfo.datasource.MarketInfoDataSource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketInfoService {
    @GET("stores/{storeId}")
    suspend fun getMarketInfo(
        @Path("storeId")
        storeId: Long,
    ): BaseResponse<MarketInfoDataSource>

    @GET("sell/stores/{storeOwnerId}")
    suspend fun getMarketProductSellItems(
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
        @Path("storeOwnerId")
        storeOwnerId: Long,
    ): BaseResponse<ProductSellItemsResponse>

    @GET("buy/stores/{storeOwnerId}")
    suspend fun getMarketProductBuyItems(
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
        @Path("storeOwnerId")
        storeOwnerId: Long,
    ): BaseResponse<ProductBuyItemsResponse>
}