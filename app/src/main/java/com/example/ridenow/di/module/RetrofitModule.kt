package com.example.ridenow.di.module

import com.example.ridenow.data.RetrofitInitialize
import com.example.ridenow.data.repository.RetrofitRepository
import com.example.ridenow.data.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        return RetrofitInitialize.initializeRetrofit()
    }

    @Singleton
    @Provides
    fun provideRetrofitRepository(retrofitService: RetrofitService): RetrofitRepository {
        return RetrofitRepository(retrofitService)
    }

}