package com.example.ridenow.ui.selectAddress

data class SelectAddressUiState(
    val idUser: String = "",
    val onChangeIdUser : (String) -> Unit = {},
    val initLocation: String = "",
    val onChangedInitLocation: (String) -> Unit = {},
    val destination: String = "",
    val onChangedDestination: (String) -> Unit = {},
    val travelEstimateValue : String = "",
)
