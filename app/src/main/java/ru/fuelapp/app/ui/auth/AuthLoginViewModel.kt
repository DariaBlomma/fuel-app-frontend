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
import ru.fuelapp.app.ui.form.minLength
import ru.fuelapp.app.ui.form.required

class AuthLoginViewModel : ViewModel() {

    val emailField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Введите email"),
            email("Некорректный email")
        )
    )

    val passwordField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Введите пароль"),
            minLength(6, "Минимум 6 символов")
        )
    )

    private val _rememberMe = MutableStateFlow(false)
    val rememberMe: StateFlow<Boolean> = _rememberMe.asStateFlow()

    private val _uiState = MutableStateFlow<AuthLoginUiState>(AuthLoginUiState.Idle)
    val uiState: StateFlow<AuthLoginUiState> = _uiState.asStateFlow()

    fun onRememberMeChange(value: Boolean) {
        _rememberMe.update { value }
    }

    fun onLoginClick() {
        if (_uiState.value is AuthLoginUiState.Loading) return

        val results = listOf(emailField, passwordField).map { it.validate() }
        if (!results.all { it }) return

        viewModelScope.launch {
            _uiState.update { AuthLoginUiState.Loading }
            delay(1500)
            _uiState.update { AuthLoginUiState.Success }
        }
    }

    fun resetForm() {
        emailField.reset()
        passwordField.reset()
    }

    fun resetState() {
        _uiState.update { AuthLoginUiState.Idle }
    }
}