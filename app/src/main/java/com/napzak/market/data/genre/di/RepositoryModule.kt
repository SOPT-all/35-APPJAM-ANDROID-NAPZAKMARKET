package com.napzak.market.data.genre.di

import com.napzak.market.data.genre.repositoryimpl.GenreRepositoryImpl
import com.napzak.market.data.genre.repositoryimpl.ImageGenreRepositoryImpl
import com.napzak.market.domain.genre.respository.GenreRepository
import com.napzak.market.domain.genre.respository.ImageGenreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl,
    ): GenreRepository

    @Binds
    @Singleton
    abstract fun bindImageGenreRepository(
        imageGenreRepositoryImpl: ImageGenreRepositoryImpl,
    ): ImageGenreRepository
}
