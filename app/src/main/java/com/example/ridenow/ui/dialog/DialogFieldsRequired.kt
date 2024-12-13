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
private fun DialogFieldsRequiredPrev() {
    DialogFieldsRequired()
}

@Composable
fun DialogFieldsRequired(
    onClickDismiss: () -> Unit = {},
    onClickConfirm: () -> Unit = {}
) {

    AlertDialog(
        title = { Text("Atenção") },
        text = { Text("Os valores devem ser preenchidos corretamente") },
        onDismissRequest = {
            onClickDismiss()
        },
        confirmButton = {
            Button(
                onClick = { onClickConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                ),
                ) {
                Text(text = "Ok")
            }
        }
    )
}