package com.example.ridenow.ui.selectAddress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.model.RideEstimateResponse
import com.example.ridenow.data.repository.RideRepository
import com.example.ridenow.rideInformation.RideViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectAddressViewModel @Inject constructor(
    private val rideInformation: RideViewModel,
    private val rideRepository: RideRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SelectAddressUiState())
    val uiState: StateFlow<SelectAddressUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onChangeIdUser = {
                    _uiState.value = _uiState.value.copy(
                        idUser = it
                    )
                },
                onChangedInitLocation = {initialLocation ->
                    _uiState.value = _uiState.value.copy(
                        initLocation = initialLocation
                    )
                },
                onChangedDestination = {destination ->
                    _uiState.value = _uiState.value.copy(
                        destination = destination
                    )
                    viewModelScope.launch {
                    }
                }
            )
        }
    }



    suspend fun estimateRide(customerId: String, origin: String, destination: String): Pair<Int, RideEstimateResponse?> {
        try {
            val request = RideEstimateRequest(customerId, origin, destination)
            val response = rideRepository.estimateRide(request)
            response.second?.let {
                rideInformation.updateRideInfo(
                    request.customer_id,
                    it.origin,
                    it.destination,
                    it.options,
                    it.duration,
                    it.distance
                )
            }
            if(customerId == "" || origin == "" || destination == ""){
                return Pair(400, null)
            }
            if(response.first == 200 && response.second?.options?.isEmpty() == true ){
                return Pair(400, null)
            }
            return Pair(response.first, response.second)

        } catch (e: Exception) {
           return Pair(400, null)
        }
    }
}