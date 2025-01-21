package com.napzak.market.data.explore.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.explore.service.ExploreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesExploreService(
        @JWT retrofit: Retrofit
    ): ExploreService = retrofit.create()
}
