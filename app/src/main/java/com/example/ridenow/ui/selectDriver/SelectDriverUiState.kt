package com.example.ridenow.ui.selectDriver

import com.example.ridenow.data.model.RideOption

data class SelectDriverUiState(
    val driverSelect: RideOption = RideOption(),
    val onChangeDriverSelect: (RideOption) -> Unit = {},
    val onCardLongPress: Boolean = false,
    val onChangeCardLongPress: (Boolean) -> Unit = { },
    val idUser: String = "",
    val listDriver: List<RideOption> = emptyList()
)



