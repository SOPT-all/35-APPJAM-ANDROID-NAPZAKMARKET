package com.napzak.market.data.genre.datasource

import com.napzak.market.data.genre.dto.GenreRegisterRequest
import com.napzak.market.data.genre.service.GenreManagementService
import javax.inject.Inject

class GenreManagementDataSource @Inject constructor(
    private val service: GenreManagementService
) {
    suspend fun postGenre(genreIds: List<Long>) =
        service.postGenre(GenreRegisterRequest(genreIds = genreIds))
}