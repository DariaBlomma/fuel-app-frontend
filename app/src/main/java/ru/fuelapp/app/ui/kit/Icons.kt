package ru.fuelapp.app.ui.kit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons24.Camera: ImageVector
    get() = Builder(
        name = "Camera", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(
            fill = null, stroke = SolidColor(Color.White), strokeLineWidth = 2f,
            strokeAlpha = 1f, strokeLineCap = Butt, strokeLineJoin = Miter
        ) {
            moveTo(9f, 3f); lineTo(15f, 3f); lineTo(17f, 6f); lineTo(21f, 6f)
            curveTo(22.1f, 6f, 23f, 6.9f, 23f, 8f)
            lineTo(23f, 20f)
            curveTo(23f, 21.1f, 22.1f, 22f, 21f, 22f)
            lineTo(3f, 22f)
            curveTo(1.9f, 22f, 1f, 21.1f, 1f, 20f)
            lineTo(1f, 8f)
            curveTo(1f, 6.9f, 1.9f, 6f, 3f, 6f)
            lineTo(7f, 6f); close()
        }
        path(fill = null, stroke = SolidColor(Color.White), strokeLineWidth = 2f) {
            moveTo(16f, 13f)
            arcTo(4f, 4f, 0f, false, true, 12f, 17f)
            arcTo(4f, 4f, 0f, false, true, 8f, 13f)
            arcTo(4f, 4f, 0f, false, true, 12f, 9f)
            arcTo(4f, 4f, 0f, false, true, 16f, 13f)
            close()
        }
    }.build()

val Icons24.Speedometer: ImageVector
    get() = Builder(
        name = "Speedometer", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(fill = null, stroke = SolidColor(Color.White), strokeLineWidth = 2f) {
            moveTo(4f, 15f)
            arcTo(8f, 8f, 0f, false, true, 20f, 15f)
        }
        path(fill = SolidColor(Color.White)) {
            moveTo(12f, 15f); lineTo(16f, 8f); lineTo(17f, 9f); lineTo(12f, 15f); close()
        }
        path(fill = SolidColor(Color.White)) {
            moveTo(12f, 15f); arcTo(1.5f, 1.5f, 0f, false, true, 10.5f, 16.5f)
            arcTo(1.5f, 1.5f, 0f, false, true, 12f, 15f); close()
        }
    }.build()

val Icons24.ArrowRight: ImageVector
    get() = Builder(
        name = "ArrowRight", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(fill = null, stroke = SolidColor(Color(0xFF9E9E9E)), strokeLineWidth = 2f) {
            moveTo(9f, 6f); lineTo(15f, 12f); lineTo(9f, 18f)
        }
    }.build()

val Icons24.ArrowDown: ImageVector
    get() = Builder(
        name = "ArrowDown", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(fill = null, stroke = SolidColor(Color(0xFF9E9E9E)), strokeLineWidth = 2f) {
            moveTo(6f, 9f); lineTo(12f, 15f); lineTo(18f, 9f)
        }
    }.build()

val Icons24.Check: ImageVector
    get() = Builder(
        name = "Check", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(fill = null, stroke = SolidColor(Color(0xFF84D2A3)), strokeLineWidth = 3f) {
            moveTo(4f, 12f); lineTo(10f, 18f); lineTo(20f, 6f)
        }
    }.build()

val Icons24.FuelDrop: ImageVector
    get() = Builder(
        name = "FuelDrop", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f
    ).apply {
        path(fill = SolidColor(Color(0xFF9E9E9E))) {
            moveTo(12f, 2f)
            curveTo(12f, 2f, 5f, 11f, 5f, 15f)
            arcTo(7f, 7f, 0f, false, false, 19f, 15f)
            curveTo(19f, 11f, 12f, 2f, 12f, 2f); close()
        }
    }.build()

val Icons24.TabScan: ImageVector
    get() = Builder(name = "TabScan", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
        path(fill = null, stroke = SolidColor(Color.Unspecified), strokeLineWidth = 2f) {
            moveTo(3f, 7f); lineTo(3f, 5f); curveTo(3f, 3.9f, 3.9f, 3f, 5f, 3f); lineTo(7f, 3f)
            moveTo(17f, 3f); lineTo(19f, 3f); curveTo(20.1f, 3f, 21f, 3.9f, 21f, 5f); lineTo(21f, 7f)
            moveTo(21f, 17f); lineTo(21f, 19f); curveTo(21f, 20.1f, 20.1f, 21f, 19f, 21f); lineTo(17f, 21f)
            moveTo(7f, 21f); lineTo(5f, 21f); curveTo(3.9f, 21f, 3f, 20.1f, 3f, 19f); lineTo(3f, 17f)
        }
        path(fill = SolidColor(Color.Unspecified)) {
            moveTo(7f, 11f); lineTo(17f, 11f); lineTo(17f, 13f); lineTo(7f, 13f); close()
        }
    }.build()

val Icons24.TabHistory: ImageVector
    get() = Builder(name = "TabHistory", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
        path(fill = null, stroke = SolidColor(Color.Unspecified), strokeLineWidth = 2f) {
            moveTo(3f, 12f)
            arcTo(9f, 9f, 0f, false, true, 21f, 12f)
            arcTo(9f, 9f, 0f, false, true, 3f, 12f); close()
            moveTo(12f, 7f); lineTo(12f, 12f); lineTo(15f, 15f)
        }
    }.build()

val Icons24.TabReports: ImageVector
    get() = Builder(name = "TabReports", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
        path(fill = SolidColor(Color.Unspecified)) {
            moveTo(5f, 20f); lineTo(5f, 10f); lineTo(9f, 10f); lineTo(9f, 20f); close()
            moveTo(11f, 20f); lineTo(11f, 4f); lineTo(15f, 4f); lineTo(15f, 20f); close()
            moveTo(17f, 20f); lineTo(17f, 13f); lineTo(21f, 13f); lineTo(21f, 20f); close()
        }
    }.build()

val Icons24.TabSettings: ImageVector
    get() = Builder(name = "TabSettings", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
        viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
        path(fill = null, stroke = SolidColor(Color.Unspecified), strokeLineWidth = 2f) {
            moveTo(12f, 15f)
            arcTo(3f, 3f, 0f, false, true, 9f, 12f)
            arcTo(3f, 3f, 0f, false, true, 12f, 9f)
            arcTo(3f, 3f, 0f, false, true, 15f, 12f)
            arcTo(3f, 3f, 0f, false, true, 12f, 15f); close()
            moveTo(19.4f, 15f)
            curveTo(19.2f, 15.3f, 19.2f, 15.8f, 19.4f, 16.1f)
            lineTo(21.5f, 18.4f)
            curveTo(21.7f, 18.6f, 21.7f, 18.9f, 21.5f, 19.1f)
            lineTo(19.1f, 21.5f)
            curveTo(18.9f, 21.7f, 18.6f, 21.7f, 18.4f, 21.5f)
            lineTo(16.1f, 19.4f)
            curveTo(15.8f, 19.2f, 15.3f, 19.2f, 15f, 19.4f)
            curveTo(14.7f, 19.5f, 14.5f, 19.8f, 14.5f, 20.2f)
            lineTo(14.5f, 22.5f)
            curveTo(14.5f, 22.8f, 14.3f, 23f, 14f, 23f)
            lineTo(10f, 23f)
            curveTo(9.7f, 23f, 9.5f, 22.8f, 9.5f, 22.5f)
            lineTo(9.5f, 20.1f)
            curveTo(9.5f, 19.8f, 9.3f, 19.5f, 9f, 19.4f)
            curveTo(8.7f, 19.2f, 8.2f, 19.2f, 7.9f, 19.4f)
            lineTo(5.6f, 21.5f)
            curveTo(5.4f, 21.7f, 5.1f, 21.7f, 4.9f, 21.5f)
            lineTo(2.5f, 19.1f)
            curveTo(2.3f, 18.9f, 2.3f, 18.6f, 2.5f, 18.4f)
            lineTo(4.6f, 16.1f)
            curveTo(4.8f, 15.8f, 4.8f, 15.3f, 4.6f, 15f)
            curveTo(4.5f, 14.7f, 4.2f, 14.5f, 3.8f, 14.5f)
            lineTo(1.5f, 14.5f)
            curveTo(1.2f, 14.5f, 1f, 14.3f, 1f, 14f)
            lineTo(1f, 10f)
            curveTo(1f, 9.7f, 1.2f, 9.5f, 1.5f, 9.5f)
            lineTo(3.9f, 9.5f)
            curveTo(4.2f, 9.5f, 4.5f, 9.3f, 4.6f, 9f)
            curveTo(4.8f, 8.7f, 4.8f, 8.2f, 4.6f, 7.9f)
            lineTo(2.5f, 5.6f)
            curveTo(2.3f, 5.4f, 2.3f, 5.1f, 2.5f, 4.9f)
            lineTo(4.9f, 2.5f)
            curveTo(5.1f, 2.3f, 5.4f, 2.3f, 5.6f, 2.5f)
            lineTo(7.9f, 4.6f)
            curveTo(8.2f, 4.8f, 8.7f, 4.8f, 9f, 4.6f)
            curveTo(9.3f, 4.5f, 9.5f, 4.2f, 9.5f, 3.8f)
            lineTo(9.5f, 1.5f)
            curveTo(9.5f, 1.2f, 9.7f, 1f, 10f, 1f)
            lineTo(14f, 1f)
            curveTo(14.3f, 1f, 14.5f, 1.2f, 14.5f, 1.5f)
            lineTo(14.5f, 3.9f)
            curveTo(14.5f, 4.2f, 14.7f, 4.5f, 15f, 4.6f)
            curveTo(15.3f, 4.8f, 15.8f, 4.8f, 16.1f, 4.6f)
            lineTo(18.4f, 2.5f)
            curveTo(18.6f, 2.3f, 18.9f, 2.3f, 19.1f, 2.5f)
            lineTo(21.5f, 4.9f)
            curveTo(21.7f, 5.1f, 21.7f, 5.4f, 21.5f, 5.6f)
            lineTo(19.4f, 7.9f)
            curveTo(19.2f, 8.2f, 19.2f, 8.7f, 19.4f, 9f)
            curveTo(19.5f, 9.3f, 19.8f, 9.5f, 20.2f, 9.5f)
            lineTo(22.5f, 9.5f)
            curveTo(22.8f, 9.5f, 23f, 9.7f, 23f, 10f)
            lineTo(23f, 14f)
            curveTo(23f, 14.3f, 22.8f, 14.5f, 22.5f, 14.5f)
            lineTo(20.1f, 14.5f)
            curveTo(19.8f, 14.5f, 19.5f, 14.7f, 19.4f, 15f); close()
        }
    }.build()

object Icons24