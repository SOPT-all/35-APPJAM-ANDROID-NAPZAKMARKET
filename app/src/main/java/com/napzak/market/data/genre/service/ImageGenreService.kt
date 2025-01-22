package com.napzak.market.data.genre.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.ImageGenreListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageGenreService {
    @GET("onboarding/genres")
    suspend fun getInitialImageGenres(
        @Query("cursor") cursor: Long = 0,
    ): BaseResponse<ImageGenreListResponse>

    @GET("onboarding/genres/search")
    suspend fun getSearchResultImageGenres(
        @Query("searchWord") searchWord: String,
        @Query("cursor") cursor: Long = 0,
    ): BaseResponse<ImageGenreListResponse>
}
