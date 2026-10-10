package ru.fuelapp.app.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fuelapp.app.ui.kit.Icons24
import ru.fuelapp.app.ui.kit.TabHistory
import ru.fuelapp.app.ui.kit.TabReports
import ru.fuelapp.app.ui.kit.TabScan
import ru.fuelapp.app.ui.kit.TabSettings

sealed class MainTab(val route: String, val title: String, val icon: ImageVector) {
    object Scan : MainTab("scan", "Скан", Icons24.TabScan)
    object History : MainTab("history", "История", Icons24.TabHistory)
    object Reports : MainTab("reports", "Отчёты", Icons24.TabReports)
    object Settings : MainTab("settings", "Настройки", Icons24.TabSettings)
}

val MAIN_TABS = listOf(MainTab.Scan, MainTab.History, MainTab.Reports, MainTab.Settings)

@Composable
fun MainBottomBar(
    selectedRoute: String,
    onSelect: (MainTab) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        MAIN_TABS.forEach { tab ->
            val selected = selectedRoute == tab.route
            val lavender = Color(0xFF9A77D9)
            val gray = Color(0xFF9E9E9E)

            NavigationBarItem(
                selected = selected,
                onClick = { onSelect(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        tint = if (selected) lavender else gray
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        color = if (selected) lavender else gray,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}