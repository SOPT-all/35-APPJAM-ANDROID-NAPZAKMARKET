package com.napzak.market.data.genre.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.genre.dto.ImageGenresResponse
import com.napzak.market.data.genre.service.ImageGenreService
import javax.inject.Inject

class ImageGenreDataSource @Inject constructor(
    private val service: ImageGenreService,
) {
    suspend fun getInitialImageGenres(): BaseResponse<ImageGenresResponse> =
        service.getInitialImageGenres()

    suspend fun getSearchResultImageGenres(searchWord: String): BaseResponse<ImageGenresResponse> =
        service.getSearchResultImageGenres(searchWord)
}
