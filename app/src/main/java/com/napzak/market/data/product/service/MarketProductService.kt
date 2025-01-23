package com.napzak.market.data.product.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductBuyItemsResponse
import com.napzak.market.data.product.dto.response.ProductSellItemsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketProductService {
    @GET("products/sell/stores/{storeOwnerId}")
    suspend fun getMarketProductSellItems(
        @Path("storeOwnerId")
        storeId: Long?,
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
    ): BaseResponse<ProductSellItemsResponse>

    @GET("products/buy/stores/{storeOwnerId}")
    suspend fun getMarketProductBuyItems(
        @Path("storeOwnerId")
        storeId: Long?,
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
    ): BaseResponse<ProductBuyItemsResponse>
}
