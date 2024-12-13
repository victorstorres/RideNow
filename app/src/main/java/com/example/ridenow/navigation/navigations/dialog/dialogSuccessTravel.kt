package com.example.ridenow.navigation.navigations.dialog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.example.ridenow.navigation.navigations.navigateToHistoryNavigation
import com.example.ridenow.navigation.navigations.navigateToSelectedAddress
import com.example.ridenow.ui.dialog.DialogSucessTravel

private const val DIALOG_SUCESS_TRAVEL = "DialogSucessTravel"

fun NavGraphBuilder.dialogSuccessTravel(
    navController: NavHostController
) {

    dialog(route = DIALOG_SUCESS_TRAVEL) {
        DialogSucessTravel(
            onClickDismiss = { navController.navigateToSelectedAddress() },
            onClickConfirm = { navController.navigateToHistoryNavigation() }
        )
    }
}

fun NavHostController.openSuccessTravelDialog(){
    navigate(DIALOG_SUCESS_TRAVEL)
}
