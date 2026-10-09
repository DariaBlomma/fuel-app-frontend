package ru.fuelapp.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun FuelAppLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(240.dp)) {
        val lavender = Color(0xFF9A77D9)
        val pistachio = Color(0xFF84D2A3)
        val scale = minOf(size.width, size.height) / 200f

        // 1. Вертикальная палка F (фисташковая)
        drawRect(
            color = pistachio,
            topLeft = Offset(40f * scale, 40f * scale),
            size = Size(15f * scale, 120f * scale)
        )

        // 2. Перекладина A — лавандовая, рисуется ДО ног,
        //    чтобы ноги обрезали её концы по своему наклону
        drawLetterACrossbar(lavender, scale)

        // 3. Ноги A — фисташковые, поверх перекладины
        drawLetterALegs(pistachio, scale)

        // 4. Перекладины F — лавандовые, поверх
        drawRect(
            color = lavender,
            topLeft = Offset(40f * scale, 40f * scale),
            size = Size(55f * scale, 15f * scale)
        )
        drawRect(
            color = lavender,
            topLeft = Offset(40f * scale, 95f * scale),
            size = Size(75f * scale, 15f * scale)
        )
    }
}

private fun DrawScope.drawLetterACrossbar(color: Color, scale: Float) {
    // Прямоугольник с запасом по бокам: прямые торцы спрятаны под ногами,
    // видимой остаётся только часть между ними — со скошенными краями
    drawRect(
        color = color,
        topLeft = Offset(95f * scale, 107f * scale),
        size = Size(54f * scale, 10f * scale)
    )
}

private fun DrawScope.drawLetterALegs(color: Color, scale: Float) {
    val path = Path()

    // Левая нога
    path.moveTo(122f * scale, 40f * scale)
    path.lineTo(68f * scale, 160f * scale)
    path.lineTo(83f * scale, 160f * scale)
    path.lineTo(122f * scale, 73f * scale)
    path.close()

    // Правая нога
    path.moveTo(122f * scale, 40f * scale)
    path.lineTo(176f * scale, 160f * scale)
    path.lineTo(161f * scale, 160f * scale)
    path.lineTo(122f * scale, 73f * scale)
    path.close()

    drawPath(path, color)
}