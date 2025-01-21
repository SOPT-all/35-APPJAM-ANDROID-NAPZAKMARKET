package com.napzak.market.domain.genre.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre (
    val genreId: Long,
    val genreName: String,
    val genreImgUrl: String? = null, // TODO: 추후 삭제 예정
) : Parcelable