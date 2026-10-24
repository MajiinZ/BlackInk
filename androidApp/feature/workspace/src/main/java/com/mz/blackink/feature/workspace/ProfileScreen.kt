package com.mz.blackink.feature.workspace

import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(onOpenMenu: () -> Unit) {
    BlankScreenScaffold(title = "Profile", onOpenMenu = onOpenMenu) {
        // Add Profile UI here using Jetpack Compose.
    }
}