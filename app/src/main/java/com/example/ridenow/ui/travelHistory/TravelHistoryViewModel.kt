package com.example.ridenow.ui.travelHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ridenow.data.repository.RideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TravelHistoryViewModel @Inject constructor(
    private val rideRepository: RideRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TravelHistoryUiState())
    val uiState: StateFlow<TravelHistoryUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onChangeUserId = {
                    _uiState.value = _uiState.value.copy(
                        userId = it
                    )
                },
                onChangeDriverId = {
                    _uiState.value = _uiState.value.copy(
                        driverId = it
                    )

                }
            )
        }
    }

    suspend fun searchRides(customerId: String, driverId: String) {
        viewModelScope.launch {
            if(customerId.isEmpty() || driverId.isEmpty()){
                _uiState.update { state ->
                    state.copy(
                        list = emptyList(),
                        textError = "Prencha os dados de forma correta"
                    )
                }
            }else{
                rideRepository.getHistory(customerId, driverId).let {
                    val success = it.first
                    if (success) {
                        it.second?.rides?.let { list ->
                            _uiState.update { state ->
                                state.copy(
                                    list = list,
                                    textError = ""
                                )
                            }

                        }
                    } else {
                        _uiState.update { state ->
                            state.copy(
                                list = emptyList(),
                                textError = "Nenhum histórico de viagem encontrado"
                            )
                        }

                    }
                }

            }

        }
    }


}