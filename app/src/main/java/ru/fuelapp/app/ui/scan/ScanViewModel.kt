package ru.fuelapp.app.ui.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.fuelapp.app.ui.scan.components.FuelType

data class ScanUiState(
    val displayCaptured: Boolean = false,
    val odometerCaptured: Boolean = false,
    val dropdownExpanded: Boolean = false,
    val selectedFuel: FuelType? = null
)

class ScanViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    // TODO: заменить на реальные данные из SessionMock / репозитория,
    //       когда будет готов слой хранения машины пользователя.
    val carName: String = "Lada Vesta"

    fun onDisplayClick() {
        _uiState.update { it.copy(displayCaptured = !it.displayCaptured) }
    }

    fun onOdometerClick() {
        _uiState.update { it.copy(odometerCaptured = !it.odometerCaptured) }
    }

    fun onToggleDropdown() {
        _uiState.update { it.copy(dropdownExpanded = !it.dropdownExpanded) }
    }

    fun onSelectFuel(fuel: FuelType) {
        _uiState.update { it.copy(selectedFuel = fuel, dropdownExpanded = false) }
    }
}