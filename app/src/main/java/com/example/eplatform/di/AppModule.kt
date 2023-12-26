package com.example.eplatform.di

import android.content.Context
import androidx.room.Room
import com.example.db.entities.ProductDatabase
import com.example.eplatform.network.ApiClient
import com.example.eplatform.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return ApiClient.retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductDatabase  {
        return Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java, "Product_Database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}