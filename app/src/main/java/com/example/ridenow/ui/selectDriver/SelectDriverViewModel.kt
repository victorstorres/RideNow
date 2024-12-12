package com.example.ridenow.ui.selectDriver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ridenow.data.model.Driver
import com.example.ridenow.data.model.RideConfirmRequest
import com.example.ridenow.data.model.RideOption
import com.example.ridenow.data.repository.RideRepository
import com.example.ridenow.rideInformation.RideViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectDriverViewModel @Inject constructor(
    private val rideInformation: RideViewModel,
    private val rideRepository: RideRepository


) : ViewModel() {
    private val _uiState = MutableStateFlow(SelectDriverUiState())
    val uiState: StateFlow<SelectDriverUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getRideInfo()
        }
        _uiState.update { state ->
            state.copy(
                onChangeDriverSelect = {
                    _uiState.value = _uiState.value.copy(
                        driverSelect = it
                    )
                },
                onChangeCardLongPress = {
                    _uiState.value = _uiState.value.copy(
                        onCardLongPress = it
                    )
                },
            )
        }

    }

    private suspend fun getRideInfo() {
        rideInformation.getRideInfo().first().let {
            _uiState.update { state ->
                state.copy(
                    idUser = it.idUser,
                    listDriver = it.options,
                )
            }

        }
    }

    suspend fun confirmRide(
        driveSelect: RideOption
    ): Boolean {
        val rideConfirmRequest = RideConfirmRequest(
            customer_id = rideInformation.uiState.value.idUser,
            origin = rideInformation.uiState.value.origin.toString(),
            destination = rideInformation.uiState.value.destination.toString(),
            distance = rideInformation.uiState.value.distance,
            duration = rideInformation.uiState.value.duration,
            driver = Driver(driveSelect.id, driveSelect.name),
            value = driveSelect.value
        )
        return try {
            val response = rideRepository.confirmRide(rideConfirmRequest)
            response.success == true
        } catch (e: Exception) {
            false
        }
    }
}

