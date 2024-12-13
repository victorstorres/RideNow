package com.example.ridenow.map

import androidx.compose.ui.graphics.ImageBitmap
import com.google.android.gms.maps.model.LatLng


data class MapUiState(
    val origin: LatLng = LatLng(0.0, 0.0),
    val destination: LatLng = LatLng(0.0, 0.0),
    val isLoading: Boolean = false,
    val error: String? = null,
    val zoom: Int = 14,
    val midpoint: LatLng = LatLng(0.0, 0.0),
    val path: String = "",
    val markers: String = "",
    val mapImage: ImageBitmap? = null
)
