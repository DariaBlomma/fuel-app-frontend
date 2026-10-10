package ru.fuelapp.app.ui.scan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.fuelapp.app.ui.kit.Icons24
import ru.fuelapp.app.ui.kit.Camera
import ru.fuelapp.app.ui.kit.Check
import ru.fuelapp.app.ui.kit.Speedometer

@Composable
fun ScanCaptureButtons(
    displayCaptured: Boolean,
    odometerCaptured: Boolean,
    onDisplayClick: () -> Unit,
    onOdometerClick: () -> Unit
) {
    val lavender = Color(0xFF9A77D9)
    val pistachio = Color(0xFF84D2A3)
    val inactive = Color(0xFFE0E0E0)

    Box(
        modifier = Modifier.height(320.dp)
    ) {
        CaptureCircle(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopCenter),
            backgroundColor = if (displayCaptured) inactive else lavender,
            icon = Icons24.Camera,
            captured = displayCaptured,
            glowColor = lavender,
            onClick = onDisplayClick
        )

        CaptureCircle(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomCenter),
            backgroundColor = if (odometerCaptured) inactive else pistachio,
            icon = Icons24.Speedometer,
            captured = odometerCaptured,
            glowColor = pistachio,
            onClick = onOdometerClick
        )
    }
}

@Composable
private fun CaptureCircle(
    modifier: Modifier,
    backgroundColor: Color,
    icon: ImageVector,
    captured: Boolean,
    glowColor: Color,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        if (!captured) {
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(260.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    glowColor.copy(alpha = 0.35f),
                                    glowColor.copy(alpha = 0.15f),
                                    glowColor.copy(alpha = 0f)
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }
        }

        Surface(
            modifier = modifier,
            shape = CircleShape,
            color = backgroundColor,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = if (captured) Color(0xFF9E9E9E) else Color.White
                )
                if (captured) {
                    Icon(
                        imageVector = Icons24.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                            .align(Alignment.BottomEnd),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}
