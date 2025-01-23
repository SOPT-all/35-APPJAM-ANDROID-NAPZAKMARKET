package com.napzak.market.data.product.di

import com.napzak.market.data.product.repositoryimpl.ExploreProductRepositoryImpl
import com.napzak.market.data.product.repositoryimpl.MarketProductRepositoryImpl
import com.napzak.market.domain.explore.repository.ExploreProductRepository
import com.napzak.market.domain.explore.repository.MarketProductRepository
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
    abstract fun bindExploreRepository(
        exploreProductRepositoryImpl: ExploreProductRepositoryImpl
    ): ExploreProductRepository

    @Binds
    @Singleton
    abstract fun bindMarketProductRepository(
        marketProductRepositoryImpl: MarketProductRepositoryImpl
    ): MarketProductRepository
}
