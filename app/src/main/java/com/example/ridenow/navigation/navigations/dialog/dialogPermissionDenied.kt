package com.example.ridenow.navigation.navigations.dialog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.example.ridenow.ui.dialog.DialogPermissionDenied

private const val DIALOG_PERMISSION_DENIED = "DialogPermissionDenied"

fun NavGraphBuilder.dialogPermissionDenied(
    navController: NavHostController
) {

    dialog(route = DIALOG_PERMISSION_DENIED) {
        DialogPermissionDenied(
            onClickDismiss = { navController.popBackStack() },
            onClickConfirm = { navController.popBackStack() }
        )
    }
}

fun NavHostController.openDialogPermissionDenied(){
    navigate(DIALOG_PERMISSION_DENIED)
}




