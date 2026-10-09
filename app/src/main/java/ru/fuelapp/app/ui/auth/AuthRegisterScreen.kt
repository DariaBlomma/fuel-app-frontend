package ru.fuelapp.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import ru.fuelapp.app.ui.form.PasswordRequirementsChecklist
import ru.fuelapp.app.ui.form.ValidatedPasswordField
import ru.fuelapp.app.ui.form.ValidatedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthRegisterScreen(
    viewModel: AuthRegisterViewModel = viewModel(),
    onLogin: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val consent by viewModel.consentField.value.collectAsState()
    val consentError by viewModel.consentField.error.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState) {
        if (uiState is AuthRegisterUiState.Success) {
            viewModel.resetState()
            onSuccess()
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F9))
    ) {
        val minHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .pointerInput(Unit) {
                    detectTapGestures { focusManager.clearFocus() }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Регистрация",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF363F48)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ValidatedTextField(
                field = viewModel.nameField,
                label = "Имя",
                required = false,
                autoFocus = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            ValidatedTextField(
                field = viewModel.emailField,
                label = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(12.dp))

            ValidatedPasswordField(
                field = viewModel.passwordField,
                label = "Пароль",
                showErrorText = false
            )

            PasswordRequirementsChecklist(field = viewModel.passwordField)

            Spacer(modifier = Modifier.height(12.dp))

            ValidatedPasswordField(
                field = viewModel.passwordConfirmField,
                label = "Повторите пароль"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ValidatedTextField(
                field = viewModel.carField,
                label = "Машина"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = consent,
                        onCheckedChange = viewModel::onConsentChange,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF9A77D9),
                            uncheckedColor = Color(0xFF9E9E9E)
                        )
                    )
                    Text(
                        text = "Согласен с условиями и политикой конфиденциальности",
                        color = Color(0xFF424242),
                        fontSize = 14.sp
                    )
                }
                consentError?.let { message ->
                    Text(
                        text = message,
                        color = Color(0xFFE53935),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onRegisterClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9A77D9))
            ) {
                if (uiState is AuthRegisterUiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("ЗАРЕГИСТРИРОВАТЬСЯ", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            AuthSocialSection(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onLogin) {
                Text("Уже есть аккаунт? Войти", color = Color(0xFF9A77D9))
            }
        }
    }
}