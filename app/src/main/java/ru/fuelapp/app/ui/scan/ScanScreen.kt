package ru.fuelapp.app.ui.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fuelapp.app.ui.scan.components.ScanCaptureButtons
import ru.fuelapp.app.ui.scan.components.ScanCarSelector
import ru.fuelapp.app.ui.scan.components.ScanFuelDropdown

@Composable
fun ScanScreen(viewModel: ScanViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F9))
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScanCarSelector(carName = viewModel.carName)

        Spacer(modifier = Modifier.weight(1f))

        ScanCaptureButtons(
            displayCaptured = uiState.displayCaptured,
            odometerCaptured = uiState.odometerCaptured,
            onDisplayClick = viewModel::onDisplayClick,
            onOdometerClick = viewModel::onOdometerClick
        )

        Spacer(modifier = Modifier.weight(1f))

        ScanFuelDropdown(
            selected = uiState.selectedFuel,
            expanded = uiState.dropdownExpanded,
            onToggle = viewModel::onToggleDropdown,
            onSelect = viewModel::onSelectFuel
        )
    }
}