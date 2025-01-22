package com.napzak.market.data.marketinfo.mapper

import com.napzak.market.data.marketinfo.dto.MarketInfoResponse
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.marketinfo.model.MarketInfo

fun MarketInfoResponse.toMarketInfo(): MarketInfo =
    MarketInfo(
        storeId = this.storeId,
        storeNickname = this.storeNickname,
        storeDescription = this.storeDescription,
        storePhoto = this.storePhoto,
        storeCover = this.storeCover,
        genrePreferenceList = this.genrePreferences.map { genreItem ->
            Genre(
                genreId = genreItem.genreId,
                genreName = genreItem.genreName
            )
        }
    )