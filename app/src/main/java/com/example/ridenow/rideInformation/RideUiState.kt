package com.example.ridenow.rideInformation

import com.example.ridenow.data.model.RideOption
import com.google.android.gms.maps.model.LatLng

data class RideUiState(
    val origin: LatLng? = null,
    val destination: LatLng? = null,
    val options : List<RideOption> = emptyList(),
    val idUser: String = "",
    val duration: String = "",
    val distance: Double = 0.0
)
