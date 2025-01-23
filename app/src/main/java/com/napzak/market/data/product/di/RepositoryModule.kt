package com.napzak.market.data.product.di

import com.napzak.market.data.product.repositoryimpl.ExploreProductRepositoryImpl
import com.napzak.market.data.product.repositoryimpl.HomeProductRepositoryImpl
import com.napzak.market.data.product.repositoryimpl.MarketProductRepositoryImpl
import com.napzak.market.domain.product.repository.ExploreProductRepository
import com.napzak.market.domain.product.repository.HomeProductRepository
import com.napzak.market.domain.product.repository.MarketProductRepository
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
    abstract fun bindHomeProductRepository(
        homeRepositoryImpl: HomeProductRepositoryImpl
    ): HomeProductRepository

    @Binds
    @Singleton
    abstract fun bindExploreProductRepository(
        exploreProductRepositoryImpl: ExploreProductRepositoryImpl
    ): ExploreProductRepository

    @Binds
    @Singleton
    abstract fun bindMarketProductRepository(
        marketProductRepositoryImpl: MarketProductRepositoryImpl
    ): MarketProductRepository
}
