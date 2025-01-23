package com.napzak.market.data.product.di

import com.napzak.market.core.network.di.JWT
import com.napzak.market.data.product.service.ExploreProductService
import com.napzak.market.data.product.service.MarketProductService
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
    fun providesExploreService(
        @JWT retrofit: Retrofit
    ): ExploreProductService = retrofit.create()

    @Provides
    @Singleton
    fun providesMarketProductService(
        @JWT retrofit: Retrofit,
    ): MarketProductService = retrofit.create()
}
