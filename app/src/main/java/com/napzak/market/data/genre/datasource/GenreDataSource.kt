package com.napzak.market.data.genre.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.GenreListResponse
import com.napzak.market.data.genre.service.GenreService
import javax.inject.Inject

class GenreDataSource @Inject constructor(
    private val service: GenreService,
) {
    suspend fun getInitialGenreItems(): BaseResponse<GenreListResponse> =
        service.getInitialGenreItems()

    suspend fun getSearchResultGenreItems(searchWord: String): BaseResponse<GenreListResponse> =
        service.getSearchResultGenreItems(searchWord)
}