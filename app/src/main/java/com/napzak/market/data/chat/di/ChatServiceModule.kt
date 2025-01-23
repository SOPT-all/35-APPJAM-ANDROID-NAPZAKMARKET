package com.napzak.market.data.chat.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.chat.service.ChatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatServiceModule {

    @Provides
    @Singleton
    fun provideChatService(@JWT retrofit: Retrofit): ChatService = retrofit.create()
}
