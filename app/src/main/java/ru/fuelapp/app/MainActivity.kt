package ru.fuelapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.fuelapp.app.ui.auth.AuthLoginScreen
import ru.fuelapp.app.ui.theme.FuelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuelAppTheme {
                AuthLoginScreen()
            }
        }
    }
}