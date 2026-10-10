package ru.fuelapp.app.ui.kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class AppButtonVariant {
    PRIMARY,
    OUTLINED
}

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: AppButtonVariant = AppButtonVariant.PRIMARY,
    loading: Boolean = false,
    height: Dp = 52.dp
) {
    val lavender = Color(0xFF9A77D9)

    val colors = when (variant) {
        AppButtonVariant.PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = lavender,
            contentColor = Color.White
        )
        AppButtonVariant.OUTLINED -> ButtonDefaults.outlinedButtonColors(
            contentColor = lavender
        )
    }

    val border = when (variant) {
        AppButtonVariant.PRIMARY -> null
        AppButtonVariant.OUTLINED -> BorderStroke(2.dp, lavender)
    }

    val shape = when (variant) {
        AppButtonVariant.PRIMARY -> RoundedCornerShape(8.dp)
        AppButtonVariant.OUTLINED -> RoundedCornerShape(50)
    }

    val label = if (variant == AppButtonVariant.PRIMARY) text.uppercase() else text

    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = shape,
        colors = colors,
        border = border,
        enabled = !loading
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = if (variant == AppButtonVariant.PRIMARY) Color.White else lavender,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = label,
                fontSize = if (variant == AppButtonVariant.PRIMARY) 16.sp else 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}