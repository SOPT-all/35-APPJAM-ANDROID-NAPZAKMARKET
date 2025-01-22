package com.napzak.market.data.genre.repositoryimpl

import com.napzak.market.data.genre.datasource.ImageGenreDataSource
import com.napzak.market.data.genre.mapper.toGenreList
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreRepository
import javax.inject.Inject

class ImageGenreRepositoryImpl @Inject constructor(
    private val dataSource: ImageGenreDataSource,
) : GenreRepository {
    override suspend fun fetchInitialGenreItems(): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getInitialImageGenres()
            response.data.toGenreList()
        }

    override suspend fun fetchSearchResultGenreItems(searchTerm: String): Result<List<Genre>> =
        runCatching {
            val response = dataSource.getSearchResultImageGenres(searchTerm)
            response.data.toGenreList()
        }
}
