package com.example.ridenow.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ridenow.ui.theme.ButtonColor


@Preview
@Composable
fun PreviewInvalidDestinationDialog() {
    DialogInvalidDestination(
        onClickDismiss = { /* Action on dismiss */ },
        onClickConfirm = { /* Action on confirm */ }
    )
}

@Composable
fun DialogInvalidDestination(
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
        title = { Text(text = "Destino Inválido") },
        text = { Text(text = "O destino que você preencheu é inválido ou não temos motoristas suficientes") }
    )
}

