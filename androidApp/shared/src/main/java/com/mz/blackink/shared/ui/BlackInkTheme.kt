package com.mz.blackink.shared.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BlackInkColors = lightColorScheme(
    primary = Color(0xFF517DA2),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8F2FA),
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color(0xFFF0F3F5),
    onBackground = Color(0xFF202B35),
    onSurface = Color(0xFF202B35),
    onSurfaceVariant = Color(0xFF7F8C98),
    outlineVariant = Color(0xFFEBEEF0),
)

@Composable
fun BlackInkTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = BlackInkColors, content = content)
}
