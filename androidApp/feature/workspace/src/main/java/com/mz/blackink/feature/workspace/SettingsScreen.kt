package com.mz.blackink.feature.workspace

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(onOpenMenu: () -> Unit) {
    BlankScreenScaffold(title = "Settings", onOpenMenu = onOpenMenu) {
        // Add Settings UI here using Jetpack Compose.
    }
}