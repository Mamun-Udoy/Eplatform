package com.example.eplatform.di

import com.example.eplatform.network.ApiClient
import com.example.eplatform.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}