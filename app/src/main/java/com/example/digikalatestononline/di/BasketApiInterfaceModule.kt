package com.example.digikalatestononline.di

import com.example.digikalatestononline.data.remote.BasketApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BasketApiInterfaceModule {

    @Singleton
    @Provides
    fun provideBasketApiService(retrofit: Retrofit) : BasketApiInterface =
        retrofit.create(BasketApiInterface::class.java)

}