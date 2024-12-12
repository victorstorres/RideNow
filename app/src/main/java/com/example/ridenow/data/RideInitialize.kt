package com.example.ridenow.data

import com.example.ridenow.data.service.RideService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RideInitialize{

    private const val BASE_URL = "https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws"
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun initializeRide(): RideService {
        return retrofit.create(RideService::class.java)
    }

}