package com.napzak.market.data.banner.di

import com.napzak.market.data.banner.repositoryimpl.HomeBannerRepositoryImpl
import com.napzak.market.domain.banner.repository.HomeBannerRepository
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
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeBannerRepositoryImpl,
    ): HomeBannerRepository
}