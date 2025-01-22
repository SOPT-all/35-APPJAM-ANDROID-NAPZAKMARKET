package com.napzak.market.domain.genre.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreManagementRepository
import javax.inject.Inject

class GenreRegisterUseCase @Inject constructor(
    private val repository: GenreManagementRepository
) {
    suspend operator fun invoke(genres: List<Genre>): Result<Int> {
        val genreIds = genres.map { it.genreId }
        return repository.postGenre(genreIds)
    }
}