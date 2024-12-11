package com.example.ridenow.navigation.navigations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.ridenow.ui.splash.SplashScreen

const val Splash_ROUTE = "SplashRoute"

fun NavGraphBuilder.splashNavigation(navController: NavHostController) {
    composable(route = Splash_ROUTE) {
        SplashScreen()
        LaunchedEffect(true) {
            kotlinx.coroutines.delay(2000)
            navController.navigateToSelectedAddress()
        }
    }
}


