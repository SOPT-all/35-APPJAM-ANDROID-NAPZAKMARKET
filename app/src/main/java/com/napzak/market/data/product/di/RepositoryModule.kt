package com.napzak.market.data.product.di

import com.napzak.market.data.product.repositoryimpl.ExploreProductProductRepositoryImpl
import com.napzak.market.domain.explore.repository.ExploreProductRepository
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
        exploreProductRepositoryImpl: ExploreProductProductRepositoryImpl
    ): ExploreProductRepository
}
