package com.napzak.market.data.genre.repositoryimpl

import com.napzak.market.data.genre.datasource.GenreDataSource
import com.napzak.market.data.genre.mapper.toGenreList
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val dataSource: GenreDataSource,
) : GenreRepository {
    override suspend fun fetchInitialGenreItems(): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getInitialGenreItems()
            response.data.toGenreList()
        }

    override suspend fun fetchSearchResultGenreItems(searchTerm: String): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getSearchResultGenreItems(searchTerm)
            response.data.toGenreList()
        }
}
