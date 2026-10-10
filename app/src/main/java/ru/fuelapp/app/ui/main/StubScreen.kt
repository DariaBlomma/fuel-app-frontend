package ru.fuelapp.app.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun StubScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F9)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title — раздел в разработке",
            color = Color(0xFF9E9E9E),
            fontSize = 16.sp
        )
    }
}