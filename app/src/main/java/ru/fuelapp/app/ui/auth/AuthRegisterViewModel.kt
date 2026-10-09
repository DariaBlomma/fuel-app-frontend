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
import ru.fuelapp.app.ui.form.Rule
import ru.fuelapp.app.ui.form.containsDigit
import ru.fuelapp.app.ui.form.containsUpperCase
import ru.fuelapp.app.ui.form.email
import ru.fuelapp.app.ui.form.minLength
import ru.fuelapp.app.ui.form.mustBeChecked
import ru.fuelapp.app.ui.form.required

sealed interface AuthRegisterUiState {
    data object Idle : AuthRegisterUiState
    data object Loading : AuthRegisterUiState
    data object Success : AuthRegisterUiState
}

class AuthRegisterViewModel : ViewModel() {

    val nameField = FieldState(
        initialValue = "",
        rules = listOf(
            Rule("Имя слишком короткое") { it.isBlank() || it.length >= 2 }
        )
    )

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
            minLength(8, "Минимум 8 символов", inChecklist = true),
            containsDigit("Хотя бы одна цифра"),
            containsUpperCase("Хотя бы одна заглавная буква")
        )
    )

    val passwordConfirmField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Повторите пароль"),
            Rule("Пароли не совпадают") { it == passwordField.value.value }
        )
    )

    val carField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Укажите название машины"),
            minLength(2, "Слишком короткое название")
        )
    )

    val consentField = FieldState(
        initialValue = false,
        rules = listOf(
            mustBeChecked("Нужно согласие с условиями")
        )
    )

    private val _uiState = MutableStateFlow<AuthRegisterUiState>(AuthRegisterUiState.Idle)
    val uiState: StateFlow<AuthRegisterUiState> = _uiState.asStateFlow()

    init {
        passwordField.addDependent(passwordConfirmField)
    }

    fun onConsentChange(checked: Boolean) {
        consentField.onValueChange(checked)
    }

    fun onRegisterClick() {
        if (_uiState.value is AuthRegisterUiState.Loading) return

        val results = listOf(
            nameField,
            emailField,
            passwordField,
            passwordConfirmField,
            carField,
            consentField
        ).map { it.validate() }
        if (!results.all { it }) return

        viewModelScope.launch {
            _uiState.update { AuthRegisterUiState.Loading }
            delay(1500)
            _uiState.update { AuthRegisterUiState.Success }
        }
    }

    fun resetState() {
        _uiState.update { AuthRegisterUiState.Idle }
    }
}