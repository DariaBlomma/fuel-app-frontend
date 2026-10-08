package ru.fuelapp.app.ui.form

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

typealias Rule<T> = (T) -> String?

class FieldState<T>(
    val initialValue: T,
    private val rules: List<Rule<T>> = emptyList()
) {
    private val _value = MutableStateFlow(initialValue)
    val value: StateFlow<T> = _value.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var isTouched = false

    fun onValueChange(newValue: T) {
        _value.value = newValue
        if (isTouched) runValidation()
    }

    fun onBlur() {
        isTouched = true
        runValidation()
    }

    fun validate(): Boolean {
        isTouched = true
        return runValidation()
    }

    fun setError(message: String?) {
        _error.value = message
    }

    fun reset() {
        _value.value = initialValue
        _error.value = null
        isTouched = false
    }

    private fun runValidation(): Boolean {
        val firstError = rules.firstNotNullOfOrNull { rule -> rule(_value.value) }
        _error.value = firstError
        return firstError == null
    }
}