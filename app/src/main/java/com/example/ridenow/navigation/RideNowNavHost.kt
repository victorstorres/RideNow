package com.example.ridenow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ridenow.navigation.navigations.Splash_ROUTE
import com.example.ridenow.navigation.navigations.dialog.dialogFieldsRequired
import com.example.ridenow.navigation.navigations.dialog.dialogInvalidDestination
import com.example.ridenow.navigation.navigations.selectedAddressNavigation
import com.example.ridenow.navigation.navigations.splashNavigation

@Composable
fun RideNowNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Splash_ROUTE) {
        splashNavigation(navController)
        selectedAddressNavigation(navController)
        dialogFieldsRequired(navController)
        dialogInvalidDestination(navController)
    }
}
