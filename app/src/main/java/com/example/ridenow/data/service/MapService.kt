package com.example.ridenow.data.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsService {
    @GET("maps/api/staticmap")
    suspend fun getStaticMap(
        @Query("center") center: String,
        @Query("zoom") zoom: Int,
        @Query("size") size: String,
        @Query("markers") markers: String,
        @Query("path") path : String,
        @Query("key") apiKey: String
    ): Response<ResponseBody>
}