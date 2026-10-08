package ru.fuelapp.app.ui.auth

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fuelapp.app.ui.form.ValidatedPasswordField
import ru.fuelapp.app.ui.form.ValidatedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginScreen(
    viewModel: AuthLoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {}
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
            .padding(32.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "FA",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(48.dp))

        ValidatedTextField(
            field = viewModel.emailField,
            label = "Email",
            autoFocus = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ValidatedPasswordField(
            field = viewModel.passwordField,
            label = "Пароль"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = viewModel::onRememberMeChange
                )
                Text("Запомнить меня")
            }
            TextButton(onClick = viewModel::onForgotPassword) {
                Text("Забыли пароль?")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                viewModel.onLoginClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            if (uiState is AuthLoginUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("ВОЙТИ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        if (uiState is AuthLoginUiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = (uiState as AuthLoginUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                " или ",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialButton("G")
            SocialButton("🍎")
            SocialButton("VK")
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { }) {
            Text("Нет аккаунта? Зарегистрироваться")
        }
    }
}

@Composable
fun SocialButton(label: String) {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(label, fontSize = 20.sp)
        }
    }
}