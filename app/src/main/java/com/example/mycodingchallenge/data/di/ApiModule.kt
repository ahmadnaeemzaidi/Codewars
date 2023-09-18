package com.example.mycodingchallenge.data.di

import com.example.mycodingchallenge.data.ApiRepository
import com.example.mycodingchallenge.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiServiceDI(retrofit: Retrofit.Builder): ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiRepository(
        apiServiceDI: ApiService
    ): ApiRepository {
        return ApiRepository(apiServiceDI)
    }

}