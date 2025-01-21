package com.napzak.market.data.genre.di

import com.napzak.market.data.genre.dto.GenreListResponse
import com.napzak.market.domain.genre.model.Genre

fun GenreListResponse.toGenreList(): List<Genre> = genreList.map { genreItem ->
    with(genreItem) {
        Genre(
            genreId = genreId,
            genreName = genreName,
        )
    }
}
