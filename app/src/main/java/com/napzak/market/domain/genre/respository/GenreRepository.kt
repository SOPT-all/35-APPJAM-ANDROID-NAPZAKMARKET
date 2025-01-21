package com.napzak.market.domain.genre.respository

import com.napzak.market.domain.genre.model.Genre

interface GenreRepository {
    suspend fun fetchInitialGenreList(): Result<List<Genre>>

    suspend fun fetchSearchResultGenreList(searchTerm: String): Result<List<Genre>>
}