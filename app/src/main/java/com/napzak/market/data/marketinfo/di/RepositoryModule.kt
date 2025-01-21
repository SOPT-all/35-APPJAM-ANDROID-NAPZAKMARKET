package com.napzak.market.data.marketinfo.di

import com.napzak.market.data.marketinfo.repositoryimpl.MarketInfoRepositoryImpl
import com.napzak.market.domain.marketinfo.repository.MarketInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMarketRepository(
        marketInfoRepositoryImpl: MarketInfoRepositoryImpl,
    ): MarketInfoRepository
}
