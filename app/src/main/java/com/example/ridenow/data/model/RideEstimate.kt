package com.example.ridenow.data.model


import android.location.Location
import com.google.gson.annotations.SerializedName

data class RideEstimateRequest(
    val customer_id: String,
    val origin: String,
    val destination: String,
)


data class RideEstimateResponse(
    @SerializedName("origin") val origin: Location,
    @SerializedName("destination") val destination: Location,
    @SerializedName("distance") val distance: Double,
    @SerializedName("duration") val duration: String,
    @SerializedName("options") val options: List<RideOption>,
    @SerializedName("routeResponse") val routeResponse: Any // Ajuste o tipo de acordo com a resposta
)

data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class RideOption(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("vehicle") val vehicle: String,
    @SerializedName("review") val review: Review,
    @SerializedName("value") val value: Double
)

data class Review(
    @SerializedName("rating") val rating: Double,
    @SerializedName("comment") val comment: String
)
