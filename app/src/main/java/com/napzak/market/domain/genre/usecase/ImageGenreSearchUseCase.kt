package com.napzak.market.domain.genre.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.genre.respository.ImageGenreRepository
import javax.inject.Inject

class ImageGenreSearchUseCase @Inject constructor(
    private val repository: ImageGenreRepository
) {
    suspend fun invoke(searchTerm: String): Result<List<Genre>> =
        if (searchTerm.isBlank()) {
            repository.fetchInitialImageGenres()
        } else {
            repository.fetchSearchResultImageGenres(searchTerm)
        }
}
