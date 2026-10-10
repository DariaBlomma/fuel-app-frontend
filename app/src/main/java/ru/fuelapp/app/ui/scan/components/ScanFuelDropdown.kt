package ru.fuelapp.app.ui.scan.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fuelapp.app.ui.kit.ArrowDown
import ru.fuelapp.app.ui.kit.Check
import ru.fuelapp.app.ui.kit.FuelDrop
import ru.fuelapp.app.ui.kit.Icons24

data class FuelType(val id: String, val name: String)

val FUEL_TYPES = listOf(
    FuelType("methane", "Метан"),
    FuelType("ai92", "АИ-92"),
    FuelType("ai95", "АИ-95"),
    FuelType("diesel", "Дизель")
)

@Composable
fun ScanFuelDropdown(
    selected: FuelType?,
    expanded: Boolean,
    onToggle: () -> Unit,
    onSelect: (FuelType) -> Unit,
    modifier: Modifier = Modifier
) {
    val lavender = Color(0xFF9A77D9)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons24.FuelDrop,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF9E9E9E)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = selected?.name ?: "Выберите топливо",
                    color = if (selected != null) Color(0xFF363F48) else Color(0xFF9E9E9E),
                    fontSize = 16.sp,
                    fontWeight = if (selected != null) FontWeight.Medium else FontWeight.Normal
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons24.ArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF9E9E9E)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column {
                    HorizontalDivider(color = Color(0xFFE0E0E0))
                    FUEL_TYPES.forEachIndexed { index, fuel ->
                        val isSelected = fuel == selected
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(fuel) }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = fuel.name,
                                color = if (isSelected) Color.White else Color(0xFF363F48),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        start = 0.dp,
                                        end = 8.dp
                                    )
                                    .then(
                                        if (isSelected) Modifier
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                        else Modifier
                                    )
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons24.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color(0xFF84D2A3)
                                )
                            }
                        }
                        if (index < FUEL_TYPES.lastIndex) {
                            HorizontalDivider(color = Color(0xFFF0F0F0))
                        }
                    }
                }
            }
        }
    }
}