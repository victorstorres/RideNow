package com.example.ridenow.ui.selectDriver.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ridenow.R
import com.example.ridenow.data.model.Driver
import com.example.ridenow.data.model.RideConfirmRequest
import com.example.ridenow.data.model.RideOption
import com.example.ridenow.ui.selectDriver.SelectDriverUiState
import com.example.ridenow.ui.theme.ButtonColor

@Preview(showBackground = true)
@Composable
private fun SheetContentRideNowPrev() {
    SheetContentRideNow()

}

@Composable
fun SheetContentRideNow(
    state: SelectDriverUiState = SelectDriverUiState(),
    onClickSelectDriver: (String) -> Unit = {  }
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Selecione o motorista", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        LazyColumn() {
            items(state.listDriver) { driver ->
                CarCardScreen(
                    modifier = Modifier
                        .padding(10.dp),
                    driver = driver,
                    onTap = { name ->
                        state.onChangeDriverSelect(name)
                    },
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_tipe),
                tint = Color.Black,
                contentDescription = "Tipe",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Segure no componente para visualizar a descrição do motorista",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
        }
        if (state.driverSelect.isNotEmpty()) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                ),
                onClick = {
                    onClickSelectDriver(
                        state.driverSelect
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
                    .height(60.dp),

                ) {
                Text(
                    "Fazer viagem com ${state.driverSelect}",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

    }
}

@Composable
fun CarCardScreen(
    modifier: Modifier = Modifier,
    driver: RideOption,
    onTap: (RideOption) -> Unit = { }
) {

    var showDescription = remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showDescription.value = !showDescription.value
                    },
                    onTap = {
                        onTap(
                        RideOption(
                            name = driver.name,
                            id = driver.id,
                            value = driver.value
                        ))
                    }
                )
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.car_image),
                contentDescription = "Car image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Motorista: ${driver.name} ",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Veículo: ${driver.vehicle}",
                    color = Color.Gray,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                )

                Text(
                    text = "Estrelas: ${driver.review.rating}",
                    color = Color.Gray,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                )
                if (showDescription.value) {
                    Divider()
                    Text(
                        text = "Descrição: ${driver.description}",
                        color = Color.Gray,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Valor: R$ ${driver.value}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = Color.Gray
            )
        }

    }
}
