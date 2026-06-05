package com.mz.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color





@Immutable
data class ExtendedColors(
    //Button states
    val primaryHover: Color,
    val destructiveHover: Color,
    val destructiveSecondaryOutline: Color,
    val destructiveSecondaryOutlineHover: Color,
    val destructiveSecondaryOutlinePressed: Color,
    val disabledOutline: Color,
    val disabledFill: Color,
    val success: Color,
    val successOutline: Color,
    val onSuccess: Color,
    val onSuccessOutline: Color,
)

val LightColorScheme = lightColorScheme(
    primary = BlackInkBrand500,
    onPrimary = BlackInkBase1000,
    primaryContainer = BlackInkBrand100,
    onPrimaryContainer = BlackInkBrand900,

    tertiary = BlackInkBrand500,
    onTertiary = BlackInkBase1000,
    tertiaryContainer = BlackInkBrand100,
    onTertiaryContainer = BlackInkBrand900,

    secondary = BlackInkBrand1000,
    onSecondary = BlackInkBase1000,
    secondaryContainer = BlackInkBase100,
    onSecondaryContainer = BlackInkBase1000,

    error = BlackInkRed500,

    background = BlackInkBase1000,
    surface = BlackInkBase0

)

val DarkColorScheme = lightColorScheme(
    primary = BlackInkBrand500,
    onPrimary = BlackInkBase1000,
    primaryContainer = BlackInkBrand100,
    onPrimaryContainer = BlackInkBrand900,

    tertiary = BlackInkBrand500,
    onTertiary = BlackInkBase1000,
    tertiaryContainer = BlackInkBrand100,
    onTertiaryContainer = BlackInkBrand900,

    secondary = BlackInkBrand1000,
    onSecondary = BlackInkBase1000,
    secondaryContainer = BlackInkBase100,
    onSecondaryContainer = BlackInkBase1000,

    error = BlackInkRed500,

    background = BlackInkBase1000,
    surface = BlackInkBase0
)

@Composable
fun BlackInkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}