package com.napzak.market.data.dummy.di

import com.napzak.market.data.dummy.repositoryimpl.DummyRepositoryImpl
import com.napzak.market.domain.dummy.repository.DummyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DummyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl
    ): DummyRepository
}