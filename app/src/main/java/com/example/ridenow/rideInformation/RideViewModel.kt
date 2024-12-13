package com.example.ridenow.rideInformation


import androidx.lifecycle.ViewModel
import com.example.ridenow.data.model.RideOption
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RideViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RideUiState())
    val uiState = _uiState.asStateFlow()


    fun updateRideInfo(
        idUser: String,
        newOrigin: LatLng,
        newDestination: LatLng,
        newListDriver: List<RideOption>,
        duration: String,
        distance: Double
    ) {
        _uiState.update {
            it.copy(
                idUser = idUser,
                origin = newOrigin,
                destination = newDestination,
                options = newListDriver,
                duration = duration,
                distance = distance
            )
        }
    }

    suspend fun getRideInfo(): Flow<RideUiState> = flow {
        emit(
            RideUiState(
                idUser = _uiState.value.idUser,
                origin = _uiState.value.origin,
                destination = _uiState.value.destination,
                options = _uiState.value.options,
                duration = _uiState.value.duration,
                distance = _uiState.value.distance
            )
        )
    }}

