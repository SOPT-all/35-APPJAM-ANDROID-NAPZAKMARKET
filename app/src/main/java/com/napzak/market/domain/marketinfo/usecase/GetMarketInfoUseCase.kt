package com.napzak.market.domain.marketinfo.usecase

import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.domain.marketinfo.model.MarketInfo
import com.napzak.market.domain.marketinfo.repository.MarketInfoRepository
import javax.inject.Inject

class GetMarketInfoUseCase @Inject constructor(
    private val marketInfoRepository: MarketInfoRepository,
) {
    suspend operator fun invoke(
        storeId: Long,
    ): Result<MarketInfo> {
        return try {
            val response = marketInfoRepository.fetchMarketInfo(storeId).getOrThrow()
            Result.success(
                MarketInfo(
                    storeId = storeId,
                    storeNickname = response.storeNickname,
                    storeDescription = response.storeDescription,
                    storePhoto = response.storePhoto,
                    storeCover = response.storeCover,
                    genrePreferenceList = response.genrePreferenceList.map { genreItem ->
                        Genre(
                            genreId = genreItem.genreId,
                            genreName = genreItem.genreName
                        )
                    }
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}