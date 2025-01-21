package com.napzak.market.data.genre.repositoryimpl

import com.napzak.market.data.genre.datasource.GenreDataSource
import com.napzak.market.data.genre.di.toGenreList
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val dataSource: GenreDataSource,
) : GenreRepository {
    override suspend fun fetchInitialGenreList(): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getInitialGenreList()
            response.data.toGenreList()
        }

    override suspend fun fetchSearchResultGenreList(searchTerm: String): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getSearchResultGenreList(searchTerm)
            response.data.toGenreList()
        }
}