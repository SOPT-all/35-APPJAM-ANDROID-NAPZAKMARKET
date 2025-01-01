package com.napzak.market.data.dummy.di

import com.napzak.market.data.dummy.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DummyServiceModule {

    @Provides
    @Singleton
    fun provideDummyService(retrofit: Retrofit): DummyService = retrofit.create()
}