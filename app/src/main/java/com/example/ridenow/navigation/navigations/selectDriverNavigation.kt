package com.example.ridenow.navigation.navigations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.ridenow.map.MapViewModel
import com.example.ridenow.ui.selectDriver.SelectDriverScreen
import com.example.ridenow.ui.selectDriver.SelectDriverViewModel
import kotlinx.coroutines.launch

private const val SELECT_DRIVER_ROUTE = "SelectDriverRoute"

fun NavGraphBuilder.selectDriverNavigation(navController: NavHostController) {
    composable(route = SELECT_DRIVER_ROUTE) {

        val selectDriverViewModel = hiltViewModel<SelectDriverViewModel>()
        val selectDriverState by selectDriverViewModel.uiState.collectAsState()

        val mapViewModel = hiltViewModel<MapViewModel>()
        val mapState by mapViewModel.uiState.collectAsState()

        val context = LocalContext.current
        val coroutine = rememberCoroutineScope()

        SelectDriverScreen(
            selectDriverState = selectDriverState,
            onMapClick = { modifier ->
                LaunchedEffect(mapState.origin){
                    mapViewModel.getMap(context)
                }

                if(mapState.isLoading){
                    mapState.mapImage?.let {
                        Image(
                            contentScale = ContentScale.FillBounds,
                            bitmap = it,
                            contentDescription = "Mapa Estático com Rota",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                }else{
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CircularProgressIndicator()
                    }
                }
            },
            onClickSelectDriver = {
                driver ->
                coroutine.launch {
                    selectDriverViewModel.confirmRide(driver).let {

                    }
                }
            }
        )
    }
}

fun NavHostController.navigateToSelectDriver() {
    navigate(SELECT_DRIVER_ROUTE) {
        launchSingleTop = true
    }
}