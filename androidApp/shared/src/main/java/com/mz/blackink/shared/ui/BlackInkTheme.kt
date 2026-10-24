package com.mz.blackink.shared.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BlackInkColors = darkColorScheme(
    primary = Color(0xFFB7E4C7),
    onPrimary = Color(0xFF102318),
    background = Color(0xFF0D110F),
    surface = Color(0xFF151C18),
    onBackground = Color(0xFFE4EAE5),
    onSurface = Color(0xFFE4EAE5),
)

@Composable
fun BlackInkTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = BlackInkColors, content = content)
}
