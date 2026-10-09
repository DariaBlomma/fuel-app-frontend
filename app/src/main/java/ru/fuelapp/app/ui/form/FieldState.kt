package ru.fuelapp.app.ui.form

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Requirement(
    val label: String,
    val met: Boolean,
    val armed: Boolean
)

class FieldState<T>(
    val initialValue: T,
    val rules: List<Rule<T>> = emptyList()
) {
    private val _value = MutableStateFlow(initialValue)
    val value: StateFlow<T> = _value.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _requirements = MutableStateFlow<List<Requirement>>(emptyList())
    val requirements: StateFlow<List<Requirement>> = _requirements.asStateFlow()

    private var isTouched = false
    private val dependents = mutableListOf<FieldState<*>>()

    init {
        refreshRequirements()
    }

    fun onValueChange(newValue: T) {
        _value.value = newValue
        if (isTouched) runValidation()
        refreshRequirements()
        dependents.forEach { it.refreshValidation() }
    }

    fun onBlur() {
        isTouched = true
        runValidation()
        refreshRequirements()
    }

    fun validate(): Boolean {
        isTouched = true
        refreshRequirements()
        return runValidation()
    }

    fun setError(message: String?) {
        _error.value = message
    }

    fun reset() {
        _value.value = initialValue
        _error.value = null
        isTouched = false
        refreshRequirements()
    }

    fun addDependent(field: FieldState<*>) {
        dependents.add(field)
    }

    fun refreshValidation() {
        if (isTouched) runValidation()
        refreshRequirements()
    }

    private fun runValidation(): Boolean {
        val firstFailed = rules.firstOrNull { !it.check(_value.value) }
        _error.value = firstFailed?.message
        return firstFailed == null
    }

    private fun refreshRequirements() {
        val current = _value.value
        _requirements.value = rules
            .filter { it.inChecklist }
            .map { Requirement(it.message, it.check(current), isTouched) }
    }
}