package com.napzak.market.data.genre.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.genre.service.GenreManagementService
import com.napzak.market.data.genre.service.GenreService
import com.napzak.market.data.genre.service.ImageGenreService
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
    fun providesGenreService(
        @JWT retrofit: Retrofit
    ): GenreService = retrofit.create()

    @Provides
    @Singleton
    fun providesImageGenreService(
        @JWT retrofit: Retrofit
    ): ImageGenreService = retrofit.create()

    @Provides
    @Singleton
    fun providesGenreManagementService(
        @JWT retrofit: Retrofit,
    ): GenreManagementService = retrofit.create()
}
