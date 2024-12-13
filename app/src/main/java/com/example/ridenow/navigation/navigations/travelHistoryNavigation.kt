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
import com.example.ridenow.ui.travelHistory.TravelHistoryScreen
import com.example.ridenow.ui.travelHistory.TravelHistoryViewModel
import kotlinx.coroutines.launch

 const val TRAVEL_HISTORY_ROUTE = "TravelHistoryRoute"

@SuppressLint("CoroutineCreationDuringComposition")
fun NavGraphBuilder.travelHistoryNavigation(navController: NavHostController) {

    composable(TRAVEL_HISTORY_ROUTE) {
        val viewModel = hiltViewModel<TravelHistoryViewModel>()
        val state by viewModel.uiState.collectAsState()

        val coroutineScope = rememberCoroutineScope()


        TravelHistoryScreen(
            state = state,
            onClickButton = {
                coroutineScope.launch {
                    viewModel.searchRides(state.userId, state.driverId)

                }
            },
            onClickButtonHome = {
                navController.navigateToSelectedAddress()
            }
        )

    }
}

fun NavHostController.navigateToHistoryNavigation(){
    navigate(TRAVEL_HISTORY_ROUTE){
        launchSingleTop = true
    }
}
