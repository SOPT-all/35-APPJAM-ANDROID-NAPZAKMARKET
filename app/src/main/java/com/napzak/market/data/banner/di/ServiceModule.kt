package com.napzak.market.data.banner.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.banner.service.HomeBannerService
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
    fun providesHomeBannerService(
        @JWT retrofit: Retrofit
    ): HomeBannerService = retrofit.create()

}