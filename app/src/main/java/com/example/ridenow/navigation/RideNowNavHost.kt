package com.example.ridenow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ridenow.navigation.navigations.TRAVEL_HISTORY_ROUTE
import com.example.ridenow.navigation.navigations.dialog.dialogFieldsRequired
import com.example.ridenow.navigation.navigations.dialog.dialogInvalidDestination
import com.example.ridenow.navigation.navigations.dialog.dialogPermissionDenied
import com.example.ridenow.navigation.navigations.dialog.dialogSuccessTravel
import com.example.ridenow.navigation.navigations.selectAddressNavigation
import com.example.ridenow.navigation.navigations.selectDriverNavigation
import com.example.ridenow.navigation.navigations.splashNavigation
import com.example.ridenow.navigation.navigations.travelHistoryNavigation

@Composable
fun RideNowNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = TRAVEL_HISTORY_ROUTE) {
        splashNavigation(navController)
        selectAddressNavigation(navController)
        selectDriverNavigation(navController)
        dialogFieldsRequired(navController)
        dialogSuccessTravel(navController)
        dialogInvalidDestination(navController)
        dialogPermissionDenied(navController)
        travelHistoryNavigation(navController)
    }
}
