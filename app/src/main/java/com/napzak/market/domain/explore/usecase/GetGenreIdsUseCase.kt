package com.napzak.market.domain.explore.usecase

import com.napzak.market.domain.genre.model.Genre

class GetGenreIdsUseCase {
    operator fun invoke(genres: List<Genre>): List<Long>? {
        return if (genres.isNotEmpty()) {
            genres.map { it.genreId }
        } else {
            null
        }
    }
}