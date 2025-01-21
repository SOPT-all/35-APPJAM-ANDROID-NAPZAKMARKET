package com.napzak.market.data.marketinfo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenrePreference(
    @SerialName("genreId")
    val genreId: Long,
    @SerialName("genreName")
    val genreName: String,
)
