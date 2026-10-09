package ru.fuelapp.app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.fuelapp.app.ui.form.FieldState
import ru.fuelapp.app.ui.form.Rule
import ru.fuelapp.app.ui.form.code
import ru.fuelapp.app.ui.form.containsDigit
import ru.fuelapp.app.ui.form.containsUpperCase
import ru.fuelapp.app.ui.form.minLength
import ru.fuelapp.app.ui.form.required

sealed interface AuthResetUiState {
    data object Idle : AuthResetUiState
    data object Loading : AuthResetUiState
    data class Error(val message: String) : AuthResetUiState
    data object Success : AuthResetUiState
}

class AuthResetViewModel(
    val email: String
) : ViewModel() {

    val codeField = FieldState(
        initialValue = "",
        rules = listOf(
            required("Введите код"),
            code(6, "Код состоит из 6 цифр")
        )
    )

    val newPasswordField = FieldState(
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
            Rule("Пароли не совпадают") { it == newPasswordField.value.value }
        )
    )

    private val _uiState = MutableStateFlow<AuthResetUiState>(AuthResetUiState.Idle)
    val uiState: StateFlow<AuthResetUiState> = _uiState.asStateFlow()

    private val _secondsLeft = MutableStateFlow(TIMER_SECONDS)
    val secondsLeft: StateFlow<Int> = _secondsLeft.asStateFlow()

    private var timerJob: Job? = null

    init {
        newPasswordField.addDependent(passwordConfirmField)
        startTimer()
    }

    fun onResendCode() {
        startTimer()
    }

    fun onSaveClick() {
        if (_uiState.value is AuthResetUiState.Loading) return

        val results = listOf(codeField, newPasswordField, passwordConfirmField).map { it.validate() }
        if (!results.all { it }) return

        viewModelScope.launch {
            _uiState.update { AuthResetUiState.Loading }
            delay(1500)
            _uiState.update { AuthResetUiState.Success }
        }
    }

    fun resetState() {
        _uiState.update { AuthResetUiState.Idle }
    }

    private fun startTimer() {
        timerJob?.cancel()
        _secondsLeft.value = TIMER_SECONDS
        timerJob = viewModelScope.launch {
            while (_secondsLeft.value > 0) {
                delay(1000)
                _secondsLeft.value -= 1
            }
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }

    private companion object {
        const val TIMER_SECONDS = 60
    }
}