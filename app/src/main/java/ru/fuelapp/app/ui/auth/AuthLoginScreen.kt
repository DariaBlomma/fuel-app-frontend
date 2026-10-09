package ru.fuelapp.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import ru.fuelapp.app.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fuelapp.app.ui.components.FuelAppLogo
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
            .background(Color(0xFFf7f7f9))
            .padding(32.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))

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
                    onCheckedChange = viewModel::onRememberMeChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF9A77D9),
                        uncheckedColor = Color(0xFF9E9E9E)
                    )
                )
                Text("Запомнить меня", color = Color(0xFF424242))
            }
            TextButton(onClick = viewModel::onForgotPassword) {
                Text("Забыли пароль?", color = Color(0xFF9A77D9))
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
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9A77D9)
            )
        ) {
            if (uiState is AuthLoginUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text("ВОЙТИ", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        FuelAppLogo(modifier = Modifier.padding(vertical = 48.dp))

        Spacer(modifier = Modifier.height(48.dp))

        // Разделитель "или"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(
                " или ",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFF9E9E9E)
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Соцсети: 3 в верхнем ряду, 2 в нижнем
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SocialButton(iconResId = R.drawable.ic_yandex)
                SocialButton(iconResId = R.drawable.ic_vk)
                SocialButton(iconResId = R.drawable.ic_max)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SocialButton(iconResId = R.drawable.ic_gosuslugi)
                SocialButton(iconResId = R.drawable.ic_sber)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { }) {
            Text("Нет аккаунта? Зарегистрироваться", color = Color(0xFF9A77D9))
        }
    }
}

@Composable
fun SocialButton(
    iconResId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(64.dp),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified
            )
        }
    }
}