package com.example.ridenow.data.repository

import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import com.example.ridenow.data.service.RetrofitService
import retrofit2.Response


class RetrofitRepository(private val retrofitService: RetrofitService) {

    suspend fun estimateRide(request: RideEstimateRequest): Pair<Int, RideEstimateResponse?> {
        val response: Response<RideEstimateResponse> = retrofitService.estimateRide(request)

        return if (response.isSuccessful) {
            val rideEstimateResponse = response.body()
            Pair(200, rideEstimateResponse)
        } else {
            Pair(response.code(), null)
        }
    }
}