package com.napzak.market.data.detailpage.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.detailpage.service.DetailPageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailPageServiceModule {
    @Provides
    @Singleton
    fun provideDetailPageService(@JWT retrofit: Retrofit): DetailPageService =
        retrofit.create(DetailPageService::class.java)
}
