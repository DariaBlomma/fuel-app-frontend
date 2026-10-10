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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fuelapp.app.ui.components.FuelAppLogo
import ru.fuelapp.app.ui.form.ValidatedPasswordField
import ru.fuelapp.app.ui.form.ValidatedTextField
import ru.fuelapp.app.ui.kit.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginScreen(
    viewModel: AuthLoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onRegister: () -> Unit = {}
) {
    val rememberMe by viewModel.rememberMe.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState) {
        if (uiState is AuthLoginUiState.Success) {
            viewModel.resetForm()
            onLoginSuccess()
            viewModel.resetState()
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
        ValidatedTextField(
            field = viewModel.emailField,
            label = "Email",
            autoFocus = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(12.dp))

        ValidatedPasswordField(
            field = viewModel.passwordField,
            label = "Пароль"
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = viewModel::onRememberMeChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF9A77D9),
                        uncheckedColor = Color(0xFF9E9E9E)
                    )
                )
                Text("Запомнить меня", color = Color(0xFF424242))
            }
            TextButton(onClick = onForgotPassword) {
                Text("Забыли пароль?", color = Color(0xFF9A77D9))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = "Войти",
            onClick = {
                focusManager.clearFocus()
                viewModel.onLoginClick()
            },
            loading = uiState is AuthLoginUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        FuelAppLogo(modifier = Modifier.size(140.dp))

        Spacer(modifier = Modifier.height(24.dp))

        AuthSocialSection(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onRegister) {
            Text("Нет аккаунта? Зарегистрироваться", color = Color(0xFF9A77D9))
        }
    }
}