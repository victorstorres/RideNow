package com.example.ridenow.data.repository

import com.example.ridenow.data.model.RideConfirmRequest
import com.example.ridenow.data.model.RideConfirmResponse
import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import com.example.ridenow.data.service.RideService
import retrofit2.Response


class RideRepository(private val rideService: RideService) {

    suspend fun estimateRide(request: RideEstimateRequest): Pair<Int, RideEstimateResponse?> {
        val response: Response<RideEstimateResponse> = rideService.estimateRide(request)

        return if (response.isSuccessful) {
            val rideEstimateResponse = response.body()
            Pair(200, rideEstimateResponse)
        } else {
            Pair(response.code(), null)
        }
    }

    suspend fun confirmRide(request: RideConfirmRequest): RideConfirmResponse {
        val response: Response<RideConfirmResponse> = rideService.confirmRide(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Erro")
        } else {
            val errorBody = response.errorBody()?.string()
            throw Exception("Erro na requisição: ${response.code()} - $errorBody")
        }
    }

}