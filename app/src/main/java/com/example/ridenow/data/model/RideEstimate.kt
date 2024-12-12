package com.example.ridenow.data.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class RideEstimateRequest(
    val customer_id: String,
    val origin: String,
    val destination: String,
)



data class RideEstimateResponse(
    @SerializedName("origin") val origin: LatLng,
    @SerializedName("destination") val destination: LatLng,
    @SerializedName("distance") val distance: Double,
    @SerializedName("duration") val duration: String,
    @SerializedName("options") val options: List<RideOption>,
    @SerializedName("routeResponse") val routeResponse: Any
)


data class RideOption(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("vehicle") val vehicle: String = "",
    @SerializedName("review") val review: Review = Review(0.0, ""),
    @SerializedName("value") val value: Double = 0.0
)

data class Review(
    @SerializedName("rating") val rating: Double,
    @SerializedName("comment") val comment: String
)
