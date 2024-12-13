package com.example.ridenow.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.ridenow.ui.theme.ButtonColor

@Composable
fun DialogSucessTravel(
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
                Text(text = "Histórico de Viagens")
            }
        },
        dismissButton = {
            Button(
                onClick = { onClickDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                ),
            ) {
                Text(text = "Cancelar")
            }

        },
        title = { Text(text = "Viagem realizada com sucesso") },
        text = { Text(text = "Se você quiser ver o histórico de viagens, selecione a opção Histórico de Viagens") }
    )
}