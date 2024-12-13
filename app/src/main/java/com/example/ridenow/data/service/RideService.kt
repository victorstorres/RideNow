package com.example.ridenow.data.service

import com.example.ridenow.data.model.RideConfirmRequest
import com.example.ridenow.data.model.RideConfirmResponse
import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import com.example.ridenow.data.model.RideHistoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RideService {

    @GET("/ride/{customer_id}")
    suspend fun getHistory(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: String
    ): Response<RideHistoryResponse>

    @POST("ride/estimate")
    suspend fun estimateRide(@Body request: RideEstimateRequest): Response<RideEstimateResponse>

    @PATCH("/ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmRequest): Response<RideConfirmResponse>

}