package com.example.ridenow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ridenow.navigation.navigations.Splash_ROUTE
import com.example.ridenow.navigation.navigations.dialog.dialogFieldsRequired
import com.example.ridenow.navigation.navigations.dialog.dialogInvalidDestination
import com.example.ridenow.navigation.navigations.dialog.dialogPermissionDenied
import com.example.ridenow.navigation.navigations.selectAddressNavigation
import com.example.ridenow.navigation.navigations.selectDriverNavigation
import com.example.ridenow.navigation.navigations.splashNavigation

@Composable
fun RideNowNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Splash_ROUTE) {
        splashNavigation(navController)
        selectAddressNavigation(navController)
        selectDriverNavigation(navController)
        dialogFieldsRequired(navController)
        dialogInvalidDestination(navController)
        dialogPermissionDenied(navController)
    }
}
