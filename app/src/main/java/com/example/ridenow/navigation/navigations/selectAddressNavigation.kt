package com.example.ridenow.navigation.navigations

import android.annotation.SuppressLint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.ridenow.navigation.navigations.dialog.openDialogInvalidDestination
import com.example.ridenow.navigation.navigations.dialog.openFieldsRequiredDialog
import com.example.ridenow.ui.selectAddress.SelectAddressScreen
import com.example.ridenow.ui.selectAddress.SelectAddressViewModel
import kotlinx.coroutines.launch

private const val SELECT_ADDRESS_ROUTE = "SelectedAddressRoute"

@SuppressLint("CoroutineCreationDuringComposition")
fun NavGraphBuilder.selectAddressNavigation(navController: NavHostController) {
    composable(route = SELECT_ADDRESS_ROUTE) {
        val viewModel = hiltViewModel<SelectAddressViewModel>()
        val state by viewModel.uiState.collectAsState()

        val coroutine = rememberCoroutineScope()


        SelectAddressScreen(
            state = state,
            onClickEstimateTravel = {
                coroutine.launch {
                    viewModel.estimateRide(
                        state.idUser,
                        state.initLocation,
                        state.destination
                    ).let {
                        when(it.first){
                            200 -> navController.navigateToSelectDriver()
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
    navigate(SELECT_ADDRESS_ROUTE) {
        launchSingleTop = true
    }
}
