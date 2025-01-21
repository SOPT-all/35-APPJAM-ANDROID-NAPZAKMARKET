package com.napzak.market.data.explore.di

import com.napzak.market.data.explore.repositoryimpl.ExploreRepositoryImpl
import com.napzak.market.domain.explore.repository.ExploreRepository
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
        exploreRepositoryImpl: ExploreRepositoryImpl
    ): ExploreRepository
}
