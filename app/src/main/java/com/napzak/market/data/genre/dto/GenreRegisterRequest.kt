package com.napzak.market.data.genre.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreRegisterRequest(
    @SerialName("genreIds")
    val genreIds: List<Long>,
)
