package com.example.ridenow.data.model

data class RideConfirmResponse(
    val success: Boolean? = null,
    val error_code: String? = null,
    val error_description: String? = null
)

data class RideConfirmRequest(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class Driver(
    val id: Int,
    val name: String
)