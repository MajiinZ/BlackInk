package com.mz.blackink.feature.workspace

import androidx.compose.runtime.Composable

@Composable
fun ContactsScreen(onOpenMenu: () -> Unit) {
    BlankScreenScaffold(title = "Contacts", onOpenMenu = onOpenMenu) {
        // Add Contacts UI here using Jetpack Compose.
    }
}