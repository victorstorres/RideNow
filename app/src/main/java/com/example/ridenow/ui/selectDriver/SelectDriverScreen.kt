package com.example.ridenow.ui.selectDriver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ridenow.data.model.RideOption
import com.example.ridenow.ui.selectDriver.components.ScaffoldRideNow
import com.example.ridenow.ui.selectDriver.components.SheetContentRideNow

@Preview
@Composable
private fun SelectDriverPrev() {
    SelectDriverScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDriverScreen(
    selectDriverState: SelectDriverUiState = SelectDriverUiState(),
    onMapClick: @Composable (Modifier) -> Unit = {},
    onClickSelectDriver: (RideOption) -> Unit = { }
) {

    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    ScaffoldRideNow(state = selectDriverState) { modifier ->
        Box(modifier = modifier) {
            BottomSheetScaffold(
                scaffoldState = sheetState,
                sheetContent = {
                    SheetContentRideNow(
                        selectDriverState,
                        onClickSelectDriver
                    )
                }) { paddingValues ->
                onMapClick(Modifier.padding(paddingValues))
            }
        }
    }
}


