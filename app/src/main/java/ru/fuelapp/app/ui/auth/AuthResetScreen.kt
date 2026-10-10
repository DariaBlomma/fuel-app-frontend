package ru.fuelapp.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fuelapp.app.ui.form.PasswordRequirementsChecklist
import ru.fuelapp.app.ui.form.ValidatedPasswordField
import ru.fuelapp.app.ui.form.ValidatedTextField
import ru.fuelapp.app.ui.kit.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthResetScreen(
    email: String,
    viewModel: AuthResetViewModel,
    onSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val secondsLeft by viewModel.secondsLeft.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState) {
        if (uiState is AuthResetUiState.Success) {
            viewModel.resetState()
            onSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F9))
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Сброс пароля",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF363F48)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Код отправлен на $email",
            color = Color(0xFF9E9E9E),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        ValidatedTextField(
            field = viewModel.codeField,
            label = "Код из письма",
            autoFocus = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (secondsLeft > 0) {
            Text(
                text = "Отправить код повторно через 0:${secondsLeft.toString().padStart(2, '0')}",
                color = Color(0xFF9E9E9E),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            TextButton(onClick = viewModel::onResendCode) {
                Text("Отправить код повторно", color = Color(0xFF9A77D9), fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        ValidatedPasswordField(
            field = viewModel.newPasswordField,
            label = "Новый пароль",
            showErrorText = false
        )

        PasswordRequirementsChecklist(field = viewModel.newPasswordField)

        Spacer(modifier = Modifier.height(12.dp))

        ValidatedPasswordField(
            field = viewModel.passwordConfirmField,
            label = "Повторите новый пароль"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = "СОХРАНИТЬ",
            onClick = {
                focusManager.clearFocus()
                viewModel.onSaveClick()
            },
            loading = uiState is AuthResetUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        )

        val state = uiState
        if (state is AuthResetUiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = state.message,
                color = Color(0xFFE53935),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}