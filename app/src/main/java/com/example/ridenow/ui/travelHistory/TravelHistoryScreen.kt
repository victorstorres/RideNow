package com.example.ridenow.ui.travelHistory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ridenow.data.model.Driver
import com.example.ridenow.data.model.Ride
import com.example.ridenow.data.model.RideHistoryResponse
import com.example.ridenow.ui.theme.ButtonColor
import com.example.ridenow.ui.theme.priceCard
import com.example.ridenow.util.formatToTwoDecimalPlaces
import com.example.ridenow.util.formatToZeroDecimalPlaces

@Preview(showSystemUi = true)
@Composable
private fun TravelHistoryScreenPreview() {
    TravelHistoryScreen()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelHistoryScreen(
    state: TravelHistoryUiState = TravelHistoryUiState(),
    onClickButton: () -> Unit = {},
    onClickButtonHome: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(
                        onClick = onClickButtonHome,
                        ) {
                        Icon(
                            imageVector = Icons.Default.Home, 
                            contentDescription = "Back Home")
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(
                text = "Historico de viagens",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.userId,
                onValueChange = state.onChangeUserId,
                label = { Text("Digite o ID do usuário") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = state.driverId,
                onValueChange = state.onChangeDriverId,
                label = { Text("Id do motorista") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                ),
                onClick = onClickButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(60.dp),

                ) {
                Text(
                    "Pesquisar",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            if (state.textError.isNotEmpty()) {
                Text(
                    text = state.textError,
                    modifier = Modifier.padding(10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 16.sp,
                    )
                )
            }
            if (state.list.isNotEmpty()) {
                LazyColumn() {
                    items(state.list) { card ->
                        CardHistory(state = card)
                    }

                }
            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun CardHistoryPrev() {
    CardHistory(
        state = Ride(
            id = 1,
            date = "05/02/2024",
            origin = "291 N Court Street",
            destination = "São Paulo",
            distance = 65.82,
            duration = "52:53",
            driver = Driver(0, ""),
            value = 0.0
        )
    )
}


@Composable
fun CardHistory(
    modifier: Modifier = Modifier,
    state: Ride
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Detalhes da Viagem",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Origem: ${state.origin}",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Data: ${state.date}",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                ),
            )

            Text(
                text = "Destino: ${state.destination}",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Distância: ${state.distance.formatToZeroDecimalPlaces()} km",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Duração: ${state.duration}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Motorista: ${state.driver.name}",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                ),
            )

            Text(
                text = "Valor: R$ ${state.value.formatToTwoDecimalPlaces()}",

                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                ),
                color = priceCard
            )
        }
    }
}