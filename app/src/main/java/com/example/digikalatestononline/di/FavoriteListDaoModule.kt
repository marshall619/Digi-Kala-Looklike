package com.example.digikalatestononline.di

import com.example.digikalatestononline.data.db.CartDao
import com.example.digikalatestononline.data.db.DigiKalaDataBase
import com.example.digikalatestononline.data.db.FavoriteListDao
import com.example.digikalatestononline.data.remote.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FavoriteListDaoModule {

    @Singleton
    @Provides
    fun provideFavoriteListDao(
        dataBase: DigiKalaDataBase
    ): FavoriteListDao = dataBase.favoriteListDao()

}