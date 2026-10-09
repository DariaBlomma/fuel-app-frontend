package ru.fuelapp.app.ui.form

import android.util.Patterns

class Rule<T>(
    val message: String,
    val inChecklist: Boolean = false,
    val check: (T) -> Boolean
)

fun required(message: String): Rule<String> =
    Rule(message) { it.isNotBlank() }

fun email(message: String): Rule<String> =
    Rule(message) { Patterns.EMAIL_ADDRESS.matcher(it).matches() }

fun minLength(length: Int, message: String, inChecklist: Boolean = false): Rule<String> =
    Rule(message, inChecklist) { it.length >= length }

fun containsDigit(message: String): Rule<String> =
    Rule(message, inChecklist = true) { it.any { c -> c.isDigit() } }

fun containsUpperCase(message: String): Rule<String> =
    Rule(message, inChecklist = true) { it.any { c -> c.isUpperCase() } }

fun code(length: Int, message: String): Rule<String> =
    Rule(message) { it.length == length && it.all { c -> c.isDigit() } }

fun mustBeChecked(message: String): Rule<Boolean> =
    Rule(message) { it }