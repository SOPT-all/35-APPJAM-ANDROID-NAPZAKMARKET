package com.napzak.market.data.genre.repositoryimpl

import com.napzak.market.data.genre.datasource.GenreManagementDataSource
import com.napzak.market.domain.genre.respository.GenreManagementRepository
import javax.inject.Inject

class GenreManagementRepositoryImpl @Inject constructor(
    private val dataSource: GenreManagementDataSource,
) : GenreManagementRepository {
    override suspend fun postGenre(genres: List<Long>): Result<Int> = runCatching {
        dataSource.postGenre(genres).status
    }
}
