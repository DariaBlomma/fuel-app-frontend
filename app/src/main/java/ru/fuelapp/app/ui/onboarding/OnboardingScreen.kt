package ru.fuelapp.app.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fuelapp.app.R
import ru.fuelapp.app.ui.kit.AppButton
import ru.fuelapp.app.ui.kit.AppButtonVariant

@Composable
fun OnboardingScreen(onFinish: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFFAFAFD), Color(0xFFE9E9F1))
                )
            )
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.onboarding_illustration),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.85f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Учёт заправок за секунды",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1C1E),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Сфотографируйте табло и одометр — мы всё распознаем автоматически",
            fontSize = 17.sp,
            color = Color(0xFF6E6E73),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            text = "Начать",
            onClick = onFinish,
            variant = AppButtonVariant.OUTLINED,
            modifier = Modifier.fillMaxWidth(0.75f),
            height = 60.dp
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}