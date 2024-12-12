package com.example.ridenow.navigation.navigations

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.ridenow.navigation.navigations.dialog.openDialogInvalidDestination
import com.example.ridenow.navigation.navigations.dialog.openFieldsRequiredDialog
import com.example.ridenow.ui.selectAddress.SelectedAddressScreen
import com.example.ridenow.ui.selectAddress.SelectedAddressViewModel
import kotlinx.coroutines.launch

private const val SELECTED_ADDRESS_ROUTE = "SelectedAddressRoute"

@SuppressLint("CoroutineCreationDuringComposition")
fun NavGraphBuilder.selectedAddressNavigation(navController: NavHostController) {
    composable(route = SELECTED_ADDRESS_ROUTE) {
        val viewModel = hiltViewModel<SelectedAddressViewModel>()
        val state by viewModel.uiState.collectAsState()
        val coroutine = rememberCoroutineScope()

//        coroutine.launch {
//            viewModel.estimateRide(
//                "custome1r123",
//                "Av. Brasil, 2033 - Jardim America, São Paulo - SP, 01431-001",
//                "Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200"
//            )
//        }

        SelectedAddressScreen(
            state = state,
            onClickEstimateTravel = {
                coroutine.launch {
                    viewModel.estimateRide(
                        state.idUser,
                        state.initLocation,
                        state.destination
                    )?.let {
                        when(it){
                            200 -> "Sucesso"
                            404 -> navController.openDialogInvalidDestination()
                            400 -> navController.openFieldsRequiredDialog()
                        }
                    }
                }
            }
        )
    }
}

fun NavHostController.navigateToSelectedAddress() {
    navigate(SELECTED_ADDRESS_ROUTE) {
        launchSingleTop = true
    }
}