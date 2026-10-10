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
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fuelapp.app.ui.form.ValidatedTextField
import ru.fuelapp.app.ui.kit.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthForgotScreen(
    viewModel: AuthForgotViewModel = viewModel(),
    onLogin: () -> Unit = {},
    onCodeSent: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState) {
        val state = uiState
        if (state is AuthForgotUiState.CodeSent) {
            viewModel.resetState()
            onCodeSent(state.email)
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
            text = "Забыли пароль?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF363F48)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Пришлём код восстановления на почту",
            color = Color(0xFF9E9E9E),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        ValidatedTextField(
            field = viewModel.emailField,
            label = "Email",
            autoFocus = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = "ОТПРАВИТЬ КОД",
            onClick = {
                focusManager.clearFocus()
                viewModel.onSendCodeClick()
            },
            loading = uiState is AuthForgotUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onLogin) {
            Text("Вспомнили пароль? Войти", color = Color(0xFF9A77D9))
        }
    }
}