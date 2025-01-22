package com.napzak.market.data.mypage.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.mypage.service.MyPageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyPageServiceModule {
    @Provides
    @Singleton
    fun provideMyPageService(
        @JWT retrofit: Retrofit,
    ): MyPageService = retrofit.create()
}
