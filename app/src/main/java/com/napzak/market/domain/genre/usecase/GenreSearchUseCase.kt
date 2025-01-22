package com.napzak.market.domain.genre.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.GenreRepository
import javax.inject.Inject

class GenreSearchUseCase @Inject constructor(
    private val genreRepository: GenreRepository,
) {
    suspend operator fun invoke(searchTerm: String): Result<List<Genre>> =
        if (searchTerm.isBlank()) {
            genreRepository.fetchInitialGenreItems()
        } else {
            genreRepository.fetchSearchResultGenreItems(searchTerm)
        }
}
