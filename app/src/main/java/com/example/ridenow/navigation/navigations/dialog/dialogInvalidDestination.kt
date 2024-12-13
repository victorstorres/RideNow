package com.example.ridenow.navigation.navigations.dialog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.example.ridenow.ui.dialog.DialogInvalidDestination

private const val DIALOG_INVALID_DESTINATION = "DialogInvalidDestination"

fun NavGraphBuilder.dialogInvalidDestination(navController: NavHostController) {
    dialog(route = DIALOG_INVALID_DESTINATION) {
        DialogInvalidDestination(
            onClickDismiss = { navController.popBackStack() },
            onClickConfirm = { navController.popBackStack() }
        )
    }
}

fun NavHostController.openDialogInvalidDestination(){
    navigate(DIALOG_INVALID_DESTINATION)
}