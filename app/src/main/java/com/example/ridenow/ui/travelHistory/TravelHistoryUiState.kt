package com.example.ridenow.ui.travelHistory

import com.example.ridenow.data.model.Ride

data class TravelHistoryUiState (
   val userId: String = "",
   val textError: String = "",
   val onChangeUserId : (String) -> Unit = {},
   val driverId: String = "",
   val onChangeDriverId : (String) -> Unit = {},
   val list : List<Ride> = emptyList()
)
