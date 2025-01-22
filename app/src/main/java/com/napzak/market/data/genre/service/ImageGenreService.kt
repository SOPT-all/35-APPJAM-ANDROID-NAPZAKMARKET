package com.napzak.market.data.genre.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.ImageGenresResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageGenreService {
    @GET("onboarding/genres")
    suspend fun getInitialImageGenres(): BaseResponse<ImageGenresResponse>

    @GET("onboarding/genres/search")
    suspend fun getSearchResultImageGenres(
        @Query("searchWord") searchWord: String,
    ): BaseResponse<ImageGenresResponse>
}
