package com.example.ridenow.data.service

import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("ride/estimate")
    suspend fun estimateRide(@Body request: RideEstimateRequest): Response<RideEstimateResponse>
}