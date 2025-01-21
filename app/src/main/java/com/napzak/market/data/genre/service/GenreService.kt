package com.napzak.market.data.genre.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.GenreListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genres")
    suspend fun getInitialGenreItems(): BaseResponse<GenreListResponse>

    @GET("genres/search")
    suspend fun getSearchResultGenreItems(
        @Query("searchWord")
        searchWord: String,
    ): BaseResponse<GenreListResponse>
}