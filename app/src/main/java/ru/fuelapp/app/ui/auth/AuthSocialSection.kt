package ru.fuelapp.app.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fuelapp.app.R

@Composable
fun AuthSocialSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(
                " или ",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFF9E9E9E)
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(R.drawable.ic_yandex)
            SocialButton(R.drawable.ic_vk)
            SocialButton(R.drawable.ic_max)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(R.drawable.ic_gosuslugi)
            SocialButton(R.drawable.ic_sber)
        }
    }
}

@Composable
fun SocialButton(iconResId: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.size(56.dp),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
        }
    }
}