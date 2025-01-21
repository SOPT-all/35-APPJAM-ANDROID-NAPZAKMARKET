package com.napzak.market.data.interest.di

import com.napzak.market.data.interest.repositoryimpl.InterestRepositoryImpl
import com.napzak.market.domain.interest.repository.InterestRepository
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
    abstract fun bindInterestRepository(
        interestRepositoryImpl: InterestRepositoryImpl
    ): InterestRepository
}