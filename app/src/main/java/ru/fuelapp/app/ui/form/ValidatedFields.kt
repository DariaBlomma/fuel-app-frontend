package ru.fuelapp.app.ui.form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ValidatedTextField(
    field: FieldState<String>,
    label: String,
    modifier: Modifier = Modifier,
    required: Boolean = true,
    autoFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    ValidatedFieldCore(
        field = field,
        label = label,
        modifier = modifier,
        required = required,
        autoFocus = autoFocus,
        keyboardOptions = keyboardOptions,
        visualTransformation = VisualTransformation.None,
        trailingIcon = null
    )
}

@Composable
fun ValidatedPasswordField(
    field: FieldState<String>,
    label: String,
    modifier: Modifier = Modifier,
    required: Boolean = true
) {
    var visible by remember { mutableStateOf(false) }

    ValidatedFieldCore(
        field = field,
        label = label,
        modifier = modifier,
        required = required,
        autoFocus = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Text(if (visible) "👁‍" else "👁‍🗨")
            }
        }
    )
}

@Composable
private fun ValidatedFieldCore(
    field: FieldState<String>,
    label: String,
    modifier: Modifier,
    required: Boolean,
    autoFocus: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    trailingIcon: (@Composable () -> Unit)?
) {
    val value by field.value.collectAsState()
    val error by field.error.collectAsState()
    val focusRequester = remember { FocusRequester() }
    var wasFocused by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (autoFocus) focusRequester.requestFocus()
    }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = field::onValueChange,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        wasFocused = true
                    } else if (wasFocused) {
                        wasFocused = false
                        field.onBlur()
                    }
                },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            singleLine = true,
            isError = error != null,
            supportingText = error?.let { message ->
                { Text(message, color = MaterialTheme.colorScheme.error) }
            }
        )
        if (required) {
            Text(
                text = "*",
                color = MaterialTheme.colorScheme.error,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
            )
        }
    }
}