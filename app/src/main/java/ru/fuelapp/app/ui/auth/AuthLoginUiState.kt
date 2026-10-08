package ru.fuelapp.app.ui.auth

sealed interface AuthLoginUiState {
    data object Idle : AuthLoginUiState
    data object Loading : AuthLoginUiState
    data class Error(val message: String) : AuthLoginUiState
    data object Success : AuthLoginUiState
}