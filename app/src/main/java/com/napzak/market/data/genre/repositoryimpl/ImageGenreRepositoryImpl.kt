package com.napzak.market.data.genre.repositoryimpl

import com.napzak.market.data.genre.datasource.ImageGenreDataSource
import com.napzak.market.data.genre.mapper.toGenreList
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.ImageGenreRepository
import javax.inject.Inject

class ImageGenreRepositoryImpl @Inject constructor(
    private val dataSource: ImageGenreDataSource,
) : ImageGenreRepository {
    override suspend fun fetchInitialImageGenres(): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getInitialImageGenres()
            response.data.toGenreList()
        }

    override suspend fun fetchSearchResultImageGenres(searchTerm: String): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getSearchResultImageGenres(searchTerm)
            response.data.toGenreList()
        }
}
