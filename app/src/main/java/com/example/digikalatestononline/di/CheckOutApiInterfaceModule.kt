package com.example.digikalatestononline.di

import com.example.digikalatestononline.data.remote.BasketApiInterface
import com.example.digikalatestononline.data.remote.CheckOutApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CheckOutApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCheckOutApiService(retrofit: Retrofit) : CheckOutApiInterface =
        retrofit.create(CheckOutApiInterface::class.java)

}