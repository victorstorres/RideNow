package com.example.ridenow.di.module

import com.example.ridenow.rideInformation.RideViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RideViewModelModule {

    @Singleton
    @Provides
    fun rideInformation(): RideViewModel {
        return RideViewModel()
    }

}