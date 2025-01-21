package com.napzak.market.data.genre.service.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse(
    @SerialName("genreList")
    val genreList: List<GenreItem>,
) {
    @Serializable
    data class GenreItem(
        @SerialName("genreId")
        val genreId: Long,
        @SerialName("genreName")
        val genreName: String,
    )
}
