package ru.fuelapp.app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.fuelapp.app.ui.form.FieldState
import ru.fuelapp.app.ui.form.email
import ru.fuelapp.app.ui.form.required

sealed interface AuthForgotUiState {
    data object Idle : AuthForgotUiState
    data object Loading : AuthForgotUiState
    data class CodeSent(val email: String) : AuthForgotUiState
}

class AuthForgotViewModel : ViewModel() {

    val emailField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Введите email"),
            email("Некорректный email")
        )
    )

    private val _uiState = MutableStateFlow<AuthForgotUiState>(AuthForgotUiState.Idle)
    val uiState: StateFlow<AuthForgotUiState> = _uiState.asStateFlow()

    fun onSendCodeClick() {
        if (_uiState.value is AuthForgotUiState.Loading) return

        if (!emailField.validate()) return

        viewModelScope.launch {
            _uiState.update { AuthForgotUiState.Loading }
            delay(1500)
            _uiState.update { AuthForgotUiState.CodeSent(emailField.value.value) }
        }
    }

    fun resetState() {
        _uiState.update { AuthForgotUiState.Idle }
    }
}