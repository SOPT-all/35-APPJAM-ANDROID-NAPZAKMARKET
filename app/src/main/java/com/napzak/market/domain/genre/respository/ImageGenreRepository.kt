package com.napzak.market.domain.genre.respository

import com.napzak.market.domain.genre.model.Genre

interface ImageGenreRepository {
    suspend fun fetchInitialImageGenres(): Result<List<Genre>>

    suspend fun fetchSearchResultImageGenres(searchTerm: String): Result<List<Genre>>
}
