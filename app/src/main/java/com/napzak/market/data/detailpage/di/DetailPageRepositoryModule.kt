package com.napzak.market.data.detailpage.di

import com.napzak.market.data.detailpage.repositoryimpl.DetailPageRepositoryImpl
import com.napzak.market.domain.detailpage.repository.DetailPageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailPageRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDetailPageRepository(
        impl: DetailPageRepositoryImpl,
    ): DetailPageRepository
}
