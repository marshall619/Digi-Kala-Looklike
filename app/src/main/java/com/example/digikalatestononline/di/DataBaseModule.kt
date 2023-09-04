package com.example.digikalatestononline.di

import android.content.Context
import androidx.room.Room
import com.example.digikalatestononline.data.db.DigiKalaDataBase
import com.example.digikalatestononline.data.remote.BasketApiInterface
import com.example.digikalatestononline.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBaseModule(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(
        context,
        DigiKalaDataBase :: class.java,
        DATABASE_NAME
    ).build()

}