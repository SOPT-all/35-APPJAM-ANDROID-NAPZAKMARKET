package com.napzak.market.data.dummy.di

import com.napzak.market.data.dummy.datasource.DummyDataSource
import com.napzak.market.data.dummy.datasourceimpl.DummyDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DummyDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindDummyDataSource(
        dummyDataSourceImpl: DummyDataSourceImpl
    ): DummyDataSource
}