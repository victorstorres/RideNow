package com.example.ridenow.ui.selectAddress

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ridenow.data.model.RideEstimateRequest
import com.example.ridenow.data.repository.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SelectedAddressViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SelectedAddressUiState())
    val uiState: StateFlow<SelectedAddressUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onChangeIdUser = {
                    _uiState.value = _uiState.value.copy(
                        idUser = it
                    )
                },
                onChangedInitLocation = {
                    _uiState.value = _uiState.value.copy(
                        initLocation = it
                    )
                },
                onChangedDestination = {
                    _uiState.value = _uiState.value.copy(
                        destination = it
                    )
                }
            )
        }
    }

    suspend fun estimateRide(customerId: String, origin: String, destination: String): Int {
        try {
            val request = RideEstimateRequest(customerId, origin, destination)
            val response = retrofitRepository.estimateRide(request)
            if(customerId == "" || origin == "" || destination == ""){
                return 400
            }
            if(response.first == 200 && response.second?.options?.isEmpty() == true ){
                return 404
            }
            return response.first

        } catch (e: Exception) {
           return 400
        }
    }
}