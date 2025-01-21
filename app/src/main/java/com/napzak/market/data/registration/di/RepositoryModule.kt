package com.napzak.market.data.registration.di

import com.napzak.market.data.registration.repositoryImpl.RegistrationRepositoryImpl
import com.napzak.market.domain.registration.repository.RegistrationRepository
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
    abstract fun bindRegistrationRepository(
        registrationRepositoryImpl: RegistrationRepositoryImpl,
    ): RegistrationRepository
}
