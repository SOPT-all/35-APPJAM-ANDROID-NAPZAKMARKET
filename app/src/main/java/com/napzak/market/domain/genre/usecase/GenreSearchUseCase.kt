package com.napzak.market.domain.genre.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreRepository
import javax.inject.Inject

class GenreSearchUseCase @Inject constructor(
    private val repository: GenreRepository,
) {
    suspend fun invoke(searchTerm: String): Result<List<Genre>> =
        if (searchTerm.isBlank()) {
            repository.fetchInitialGenreItems()
        } else {
            repository.fetchSearchResultGenreItems(searchTerm)
        }
}
