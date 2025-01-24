package com.napzak.market.data.registration.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.registration.service.ImageRegistrationService
import com.napzak.market.data.registration.service.PostRegistrationService
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
    fun providesImageRegistrationService(
        retrofit: Retrofit,
    ): ImageRegistrationService = retrofit.create()

    @Provides
    @Singleton
    fun providesPostRegistrationService(
        @JWT retrofit: Retrofit,
    ): PostRegistrationService = retrofit.create()
}
