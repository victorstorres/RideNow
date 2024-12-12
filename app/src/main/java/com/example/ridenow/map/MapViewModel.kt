package com.example.ridenow.map

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ridenow.BuildConfig
import com.example.ridenow.data.MapInitialize
import com.example.ridenow.rideInformation.RideViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Math.atan2
import java.lang.Math.sin
import java.lang.Math.sqrt
import javax.inject.Inject
import kotlin.math.cos

@HiltViewModel
class MapViewModel @Inject constructor(
    private val rideViewModel: RideViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAddress()
        }
    }

    private suspend fun getAddress() {
        rideViewModel.getRideInfo().first().let {
            val origin = it.origin ?: LatLng(0.0, 0.0)
            val destination = it.destination ?: LatLng(0.0, 0.0)
            _uiState.value = _uiState.value.copy(
                origin = origin,
                destination = destination,
                midpoint = calculateMidpoint(origin, destination),
                path = "${origin.latitude},${origin.longitude}|${destination.latitude},${destination.longitude}",
                markers = "${origin.latitude},${origin.longitude}|${destination.latitude},${destination.longitude}"
            )
        }
    }

    suspend fun getMap(context: Context) {

        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val size = "${screenWidth}x${screenHeight}"

        try {
            val origin = _uiState.value.origin
            val destination = _uiState.value.destination
            if (origin != LatLng(0.0, 0.0) && destination != LatLng(0.0, 0.0)) {
                val response = MapInitialize.mapsService.getStaticMap(
                    center = "${_uiState.value.midpoint.latitude},${_uiState.value.midpoint.longitude}",
                    zoom = 12,
                    size = size,
                    markers = _uiState.value.markers,
                    path = _uiState.value.path,
                    apiKey = BuildConfig.GOOGLE_MAPS_API_KEY
                )
                if (response.isSuccessful) {
                    val inputStream = response.body()?.byteStream()
                    val imageBitmap = BitmapFactory.decodeStream(inputStream)
                    _uiState.value = _uiState.value.copy(
                        mapImage = imageBitmap?.asImageBitmap(),
                        isLoading = true
                    )
                }
            }
        } catch (_: Exception) {

        }
    }

    private fun calculateMidpoint(origin: LatLng, destination: LatLng): LatLng {
        val lat1Rad = Math.toRadians(origin.latitude)
        val lon1Rad = Math.toRadians(origin.longitude)
        val lat2Rad = Math.toRadians(destination.latitude)
        val lon2Rad = Math.toRadians(destination.longitude)

        val dLon = lon2Rad - lon1Rad
        val Bx = cos(lat2Rad) * cos(dLon)
        val By = cos(lat2Rad) * sin(dLon)

        val latMidRad = atan2(
            sin(lat1Rad) + sin(lat2Rad),
            sqrt((cos(lat1Rad) + Bx) * (cos(lat1Rad) + Bx) + By * By)
        )
        val lonMidRad = lon1Rad + atan2(By, cos(lat1Rad) + Bx)

        val latMid = Math.toDegrees(latMidRad)
        val lonMid = Math.toDegrees(lonMidRad)

        return LatLng(latMid, lonMid)
    }

}