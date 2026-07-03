package com.mz.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalExtendedColors = staticCompositionLocalOf {LightExtendedColors}

val ColorScheme.extended: ExtendedColors
@ReadOnlyComposable
@Composable
get() = LocalExtendedColors.current

@Immutable
data class ExtendedColors(
    // Button states
    val primaryHover: Color,
    val destructiveHover: Color,
    val destructiveSecondaryOutline: Color,
    val disabledOutline: Color,
    val disabledFill: Color,
    val successOutline: Color,
    val success: Color,
    val onSuccess: Color,
    val secondaryFill: Color,

    // Text variants
    val textPrimary: Color,
    val textTertiary: Color,
    val textSecondary: Color,
    val textPlaceholder: Color,
    val textDisabled: Color,

    // Surface variants
    val surfaceLower: Color,
    val surfaceHigher: Color,
    val surfaceOutline: Color,
    val overlay: Color,

    // Accent colors
    val accentBlue: Color,
    val accentPurple: Color,
    val accentViolet: Color,
    val accentPink: Color,
    val accentOrange: Color,
    val accentYellow: Color,
    val accentGreen: Color,
    val accentTeal: Color,
    val accentLightBlue: Color,
    val accentGrey: Color,

    // Cake colors for chat bubbles
    val cakeViolet: Color,
    val cakeGreen: Color,
    val cakeBlue: Color,
    val cakePink: Color,
    val cakeOrange: Color,
    val cakeYellow: Color,
    val cakeTeal: Color,
    val cakePurple: Color,
    val cakeRed: Color,
    val cakeMint: Color,
)

val LightExtendedColors = ExtendedColors(
    primaryHover = BlackInkBase0,
    destructiveHover = BlackInkRed200,
    destructiveSecondaryOutline = BlackInkRed200,
    disabledOutline = BlackInkBase200
,
    disabledFill = BlackInkBase150,
    successOutline = BlackInkBase100,
    success = BlackInkBrand600
,
    onSuccess = BlackInkBase0
,
    secondaryFill = BlackInkBase100,

    textPrimary = BlackInkBase1000,
    textTertiary = BlackInkBase800,
    textSecondary = BlackInkBase900
,
    textPlaceholder = BlackInkBase700,
    textDisabled = BlackInkBase400,

    surfaceLower = BlackInkBase100,
    surfaceHigher = BlackInkBase100,
    surfaceOutline = BlackInkBase1000Alpha14,
    overlay = BlackInkBase1000Alpha80,

    accentBlue = BlackInkBlue,
    accentPurple = BlackInkPurple,
    accentViolet = BlackInkViolet,
    accentPink = BlackInkPink,
    accentOrange = BlackInkOrange,
    accentYellow = BlackInkYellow,
    accentGreen = BlackInkGreen,
    accentTeal = BlackInkTeal,
    accentLightBlue = BlackInkLightBlue,
    accentGrey = BlackInkGrey,

    cakeViolet = BlackInkLightViolet,
    cakeGreen = BlackInkLightGreen,
    cakeBlue = BlackInkLightBlue,
    cakePink = BlackInkLightPink,
    cakeOrange = BlackInkLightOrange,
    cakeYellow = BlackInkLightYellow,
    cakeTeal = BlackInkLightTeal,
    cakePurple = BlackInkLightPurple,
    cakeRed = BlackInkLightRed,
    cakeMint = BlackInkLightMint,
)

val DarkExtendedColors = ExtendedColors(
    primaryHover = BlackInkBrand600
,
    destructiveHover = BlackInkRed600,
    destructiveSecondaryOutline = BlackInkRed200,
    disabledOutline = BlackInkBase900
,
    disabledFill = BlackInkBase1000,
    successOutline = BlackInkBase500,
    success = BlackInkBase500
,
    onSuccess = BlackInkBase1000,
    secondaryFill = BlackInkBase900
,

    textPrimary = BlackInkBase0
,
    textTertiary = BlackInkBase200
,
    textSecondary = BlackInkBase150,
    textPlaceholder = BlackInkBase400,
    textDisabled = BlackInkBase500,

    surfaceLower = BlackInkBase1000,
    surfaceHigher = BlackInkBase900
,
    surfaceOutline = BlackInkBase100Alpha10Alt,
    overlay = BlackInkBase1000Alpha80,

    accentBlue = BlackInkBlue,
    accentPurple = BlackInkPurple,
    accentViolet = BlackInkViolet,
    accentPink = BlackInkPink,
    accentOrange = BlackInkOrange,
    accentYellow = BlackInkYellow,
    accentGreen = BlackInkGreen,
    accentTeal = BlackInkTeal,
    accentLightBlue = BlackInkLightBlue,
    accentGrey = BlackInkGrey,

    cakeViolet = BlackInkDarkViolet,
    cakeGreen = BlackInkDarkGreen,
    cakeBlue = BlackInkDarkBlue,
    cakePink = BlackInkDarkPink,
    cakeOrange = BlackInkDarkOrange,
    cakeYellow = BlackInkDarkYellow,
    cakeTeal = BlackInkDarkTeal,
    cakePurple = BlackInkDarkPurple,
    cakeRed = BlackInkDarkRed,
    cakeMint = BlackInkDarkMint,
)

val LightColorScheme = lightColorScheme(
    primary = BlackInkBase500
,
    onPrimary = BlackInkBrand1000,
    primaryContainer = BlackInkBrand100,
    onPrimaryContainer = BlackInkBrand900,

    secondary = BlackInkBase900,
    onSecondary = BlackInkBase0
,
    secondaryContainer = BlackInkBase100,
    onSecondaryContainer = BlackInkBase900
,

    tertiary = BlackInkBrand900,
    onTertiary = BlackInkBase0
,
    tertiaryContainer = BlackInkBrand100,
    onTertiaryContainer = BlackInkBrand1000,

    error = BlackInkRed500,
    onError = BlackInkBase0
,
    errorContainer = BlackInkRed200,
    onErrorContainer = BlackInkRed600,

    background = BlackInkBrand1000,
    onBackground = BlackInkBase0
,
    surface = BlackInkBase0
,
    onSurface = BlackInkBase1000,
    surfaceVariant = BlackInkBase100,
    onSurfaceVariant = BlackInkBase900
,

    outline = BlackInkBase1000Alpha8,
    outlineVariant = BlackInkBase200
,
)

val DarkColorScheme = darkColorScheme(
    primary = BlackInkBase500
,
    onPrimary = BlackInkBrand1000,
    primaryContainer = BlackInkBrand900,
    onPrimaryContainer = BlackInkBase500
,

    secondary = BlackInkBase400,
    onSecondary = BlackInkBase1000,
    secondaryContainer = BlackInkBase900
,
    onSecondaryContainer = BlackInkBase150,

    tertiary = BlackInkBase500
,
    onTertiary = BlackInkBase1000,
    tertiaryContainer = BlackInkBrand900,
    onTertiaryContainer = BlackInkBase500
,

    error = BlackInkRed500,
    onError = BlackInkBase0
,
    errorContainer = BlackInkRed600,
    onErrorContainer = BlackInkRed200,

    background = BlackInkBase1000,
    onBackground = BlackInkBase0
,
    surface = BlackInkBase950,
    onSurface = BlackInkBase0
,
    surfaceVariant = BlackInkBase900
,
    onSurfaceVariant = BlackInkBase150,

    outline = BlackInkBase100Alpha10,
    outlineVariant = BlackInkBase800,
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