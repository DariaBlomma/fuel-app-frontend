package ru.fuelapp.app.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.fuelapp.app.ui.scan.ScanScreen

@Composable
fun MainGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: MainTab.Scan.route

    Scaffold(
        bottomBar = {
            MainBottomBar(
                selectedRoute = currentRoute,
                onSelect = { tab ->
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = MainTab.Scan.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(MainTab.Scan.route) { ScanScreen() }
            composable(MainTab.History.route) { StubScreen("История") }
            composable(MainTab.Reports.route) { StubScreen("Отчёты") }
            composable(MainTab.Settings.route) { StubScreen("Настройки") }
        }
    }
}