package com.example.ridenow.navigation.navigations.dialog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.example.ridenow.ui.dialog.DialogFieldsRequired

private const val DIALOG_FIELDS_REQUIRED_ROUTE = "DialogFieldsRequiredRoute"

fun NavGraphBuilder.dialogFieldsRequired(navController: NavHostController) {
    dialog(route = DIALOG_FIELDS_REQUIRED_ROUTE) {
        DialogFieldsRequired(
            onClickDismiss = { navController.popBackStack() },
            onClickConfirm = { navController.popBackStack() }
        )
    }
}

fun NavHostController.openFieldsRequiredDialog(){
    navigate(DIALOG_FIELDS_REQUIRED_ROUTE)
}