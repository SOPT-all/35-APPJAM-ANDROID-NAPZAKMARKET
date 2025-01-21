package com.napzak.market.data.registration.di

import com.napzak.market.data.registration.service.RegistrationService
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
    fun providesRegistrationService(
        retrofit: Retrofit,
    ): RegistrationService = retrofit.create()
}
