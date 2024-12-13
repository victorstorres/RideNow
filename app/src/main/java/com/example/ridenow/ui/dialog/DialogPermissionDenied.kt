package com.example.ridenow.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.ridenow.ui.theme.ButtonColor


@Composable
fun DialogPermissionDenied(
    onClickDismiss: () -> Unit,
    onClickConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClickDismiss() },
        confirmButton = {
            Button(
                onClick = { onClickConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                ),
            ) {
                Text(text = "Ok")
            }
        },
        title = { Text(text = "Permissão Negada") },
        text = { Text(text = "Por favor, permita a localização nas configurações do seu dispositivo") }
    )
}