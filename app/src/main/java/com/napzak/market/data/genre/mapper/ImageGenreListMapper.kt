package com.napzak.market.data.genre.mapper

import com.napzak.market.data.genre.dto.ImageGenreListResponse
import com.napzak.market.domain.genre.model.Genre

fun ImageGenreListResponse.toGenreList(): List<Genre> = genreList.map { genreItem ->
    with(genreItem) {
        Genre(
            genreId = genreId,
            genreName = genreName,
            genreImgUrl = genrePhoto
        )
    }
}
