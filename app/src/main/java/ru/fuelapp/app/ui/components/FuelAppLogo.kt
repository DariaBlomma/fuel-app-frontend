package ru.fuelapp.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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

        drawLetterF(lavender, pistachio, scale)
        drawLetterACrossbar(lavender, scale)
        drawLetterALegs(pistachio, scale)
    }
}

private fun DrawScope.drawLetterF(lavender: Color, pistachio: Color, scale: Float) {
    // Низ ствола — фисташковый
    drawRect(
        color = pistachio,
        topLeft = Offset(40f * scale, 110f * scale),
        size = Size(15f * scale, 50f * scale)
    )

    // Верх ствола — лавандовый (отдельный вызов, не в общем Path)
    drawRect(
        color = lavender,
        topLeft = Offset(40f * scale, 40f * scale),
        size = Size(15f * scale, 70f * scale)
    )

    // Верхняя перекладина — отдельный Path
    val topBar = Path().apply {
        moveTo(40f * scale, 40f * scale)
        lineTo(110f * scale, 40f * scale)
        lineTo(103.25f * scale, 55f * scale)
        lineTo(40f * scale, 55f * scale)
        close()
    }
    drawPath(topBar, lavender)

    // Средняя перекладина — отдельный Path
    val middleBar = Path().apply {
        moveTo(40f * scale, 95f * scale)
        lineTo(92f * scale, 95f * scale)
        lineTo(85.25f * scale, 110f * scale)
        lineTo(40f * scale, 110f * scale)
        close()
    }
    drawPath(middleBar, lavender)
}

private fun DrawScope.drawLetterACrossbar(color: Color, scale: Float) {
    drawRect(
        color = color,
        topLeft = Offset(95f * scale, 107f * scale),
        size = Size(54f * scale, 10f * scale)
    )
}

private fun DrawScope.drawLetterALegs(color: Color, scale: Float) {
    val path = Path()

    path.moveTo(122f * scale, 40f * scale)
    path.lineTo(68f * scale, 160f * scale)
    path.lineTo(83f * scale, 160f * scale)
    path.lineTo(122f * scale, 73f * scale)
    path.close()

    path.moveTo(122f * scale, 40f * scale)
    path.lineTo(176f * scale, 160f * scale)
    path.lineTo(161f * scale, 160f * scale)
    path.lineTo(122f * scale, 73f * scale)
    path.close()

    drawPath(path, color)
}