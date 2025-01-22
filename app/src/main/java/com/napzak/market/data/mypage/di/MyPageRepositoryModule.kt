package com.napzak.market.data.mypage.di

import com.napzak.market.data.mypage.repositoryImpl.MyPageRepositoryImpl
import com.napzak.market.domain.mypage.repository.MyPageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MyPageRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyPageRepository(
        impl: MyPageRepositoryImpl,
    ): MyPageRepository
}
