package com.example.androidapp3.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(

    primary = BluePrimary,

    secondary = BlueSecondary,

    tertiary = Gold,

    background = LightBackground
)

private val DarkColorScheme = darkColorScheme(

    primary = BlueSecondary,

    secondary = BluePrimary,

    tertiary = Gold,

    background = DarkBackground
)

@Composable
fun AndroidApp3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}