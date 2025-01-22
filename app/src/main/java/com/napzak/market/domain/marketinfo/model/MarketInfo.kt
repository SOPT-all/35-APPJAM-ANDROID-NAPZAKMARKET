package com.napzak.market.domain.marketinfo.model

import com.napzak.market.domain.genre.model.Genre

data class MarketInfo(
    val storeId: Long,
    val storeNickname: String,
    val storeDescription: String,
    val storePhoto: String,
    val storeCover: String,
    val genrePreferenceList: List<Genre>,
)
