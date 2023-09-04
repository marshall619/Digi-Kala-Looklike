package com.example.digikalatestononline.di

import com.example.digikalatestononline.data.remote.AddressApiInterface
import com.example.digikalatestononline.data.remote.ProductDetailApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductDetailApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProductDetailApiService(retrofit: Retrofit) : ProductDetailApiInterface =
        retrofit.create(ProductDetailApiInterface::class.java)

}