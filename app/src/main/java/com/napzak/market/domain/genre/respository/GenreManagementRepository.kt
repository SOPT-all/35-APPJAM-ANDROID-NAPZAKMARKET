package com.napzak.market.domain.genre.respository

interface GenreManagementRepository {
    suspend fun postGenre(genres: List<Long>): Result<Int>
}
