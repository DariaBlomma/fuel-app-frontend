package ru.fuelapp.app.ui.form

import android.util.Patterns

fun required(message: String): Rule<String> = { value ->
    if (value.isBlank()) message else null
}

fun email(message: String): Rule<String> = { value ->
    if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) message else null
}

fun minLength(length: Int, message: String): Rule<String> = { value ->
    if (value.length < length) message else null
}