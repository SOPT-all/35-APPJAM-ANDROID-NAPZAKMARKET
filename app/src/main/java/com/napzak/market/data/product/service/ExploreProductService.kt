package com.napzak.market.data.product.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.product.dto.response.ProductBuyItemsResponse
import com.napzak.market.data.product.dto.response.ProductSellItemsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreProductService {
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

    @GET("products/sell/search")
    suspend fun getSearchedProductSellItems(
        @Query("searchWord")
        searchWord: String,
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

    @GET("products/buy/search")
    suspend fun getSearchedProductBuyItems(
        @Query("searchWord")
        searchWord: String,
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
