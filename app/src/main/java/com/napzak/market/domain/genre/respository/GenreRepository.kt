package com.napzak.market.domain.genre.respository

import com.napzak.market.domain.genre.model.Genre

interface GenreRepository {
    suspend fun fetchInitialGenreItems(): Result<List<Genre>>

    suspend fun fetchSearchResultGenreItems(searchTerm: String): Result<List<Genre>>
}