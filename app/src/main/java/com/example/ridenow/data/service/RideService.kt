package com.example.ridenow.data.service

import com.example.ridenow.data.model.RideConfirmRequest
import com.example.ridenow.data.model.RideConfirmResponse
import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface RideService {

    @POST("ride/estimate")
    suspend fun estimateRide(@Body request: RideEstimateRequest): Response<RideEstimateResponse>

    @PATCH("/ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmRequest): Response<RideConfirmResponse>

}