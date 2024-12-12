package com.example.ridenow.di.module

import com.example.ridenow.data.RideInitialize
import com.example.ridenow.data.repository.RideRepository
import com.example.ridenow.data.service.RideService
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
    fun provideRetrofitService(): RideService {
        return RideInitialize.initializeRide()
    }

    @Singleton
    @Provides
    fun provideRetrofitRepository(rideService: RideService): RideRepository {
        return RideRepository(rideService)
    }

}