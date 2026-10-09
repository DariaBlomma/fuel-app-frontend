package ru.fuelapp.app.ui.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordRequirementsChecklist(
    field: FieldState<String>,
    modifier: Modifier = Modifier
) {
    val requirements by field.requirements.collectAsState()

    Column(
        modifier = modifier.padding(start = 4.dp, top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        requirements.forEach { requirement ->
            val color = when {
                requirement.met -> Color(0xFF84D2A3)
                requirement.armed -> Color(0xFFE53935)
                else -> Color(0xFF9E9E9E)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (requirement.met) "✓" else "○",
                    color = color,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = requirement.label,
                    color = color,
                    fontSize = 12.sp
                )
            }
        }
    }
}