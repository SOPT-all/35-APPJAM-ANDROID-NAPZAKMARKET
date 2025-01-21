package com.napzak.market.data.home.di

import com.napzak.market.data.home.datasource.HomeDataSource
import com.napzak.market.data.home.datasource.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(
        homeDataSourceImpl: HomeDataSourceImpl
    ): HomeDataSource

}