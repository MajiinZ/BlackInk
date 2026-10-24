package com.mz.blackink.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mz.blackink.feature.home.HomeScreen
import com.mz.blackink.feature.workspace.CallsScreen
import com.mz.blackink.feature.workspace.ContactsScreen
import com.mz.blackink.feature.workspace.ProfileScreen
import com.mz.blackink.feature.workspace.SettingsScreen
import kotlinx.coroutines.launch

private enum class Destination(val route: String, val label: String, val icon: ImageVector) {
    Chats("home", "Chats", Icons.AutoMirrored.Filled.Chat),
    Contacts("contacts", "Contacts", Icons.Default.Contacts),
    Calls("calls", "Calls", Icons.Default.Call),
    Settings("settings", "Settings", Icons.Default.Settings),
    Profile("profile", "Profile", Icons.Default.Person),
    SavedMessages("saved", "Saved Messages", Icons.Default.BookmarkBorder),
}

private val tabs = listOf(Destination.Chats, Destination.Contacts, Destination.Calls, Destination.Settings)
private val drawerDestinations = listOf(Destination.Chats, Destination.Contacts, Destination.Calls, Destination.SavedMessages, Destination.Profile, Destination.Settings)

@Composable
fun BlackInkNavGraph() {
    val controller = rememberNavController()
    val backStack by controller.currentBackStackEntryAsState()
    val route = backStack?.destination?.route ?: Destination.Chats.route
    val drawer = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openMenu: () -> Unit = { scope.launch { drawer.open() } }
    val navigate: (Destination) -> Unit = { destination ->
        controller.navigate(destination.route) {
            popUpTo(controller.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    ModalNavigationDrawer(
        drawerState = drawer,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary).padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        Modifier.size(64.dp).background(MaterialTheme.colorScheme.onPrimary.copy(alpha = .18f), CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("BI", color = MaterialTheme.colorScheme.onPrimary, fontSize = 25.sp)
                    }
                    Text("BlackInk", color = MaterialTheme.colorScheme.onPrimary, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    Text("Your conversations, together", color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .8f), fontSize = 13.sp)
                }
                Column(Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(vertical = 12.dp)) {
                    drawerDestinations.forEach { destination ->
                        NavigationDrawerItem(
                            label = { Text(destination.label) },
                            icon = { Icon(destination.icon, contentDescription = null) },
                            selected = route == destination.route,
                            onClick = {
                                scope.launch {
                                    drawer.close()
                                    navigate(destination)
                                }
                            },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                        )
                    }
                }
                HorizontalDivider()
                Text("BlackInk for Android · Local demo", Modifier.padding(24.dp), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }
        },
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                    tabs.forEach { destination ->
                        NavigationBarItem(
                            selected = route == destination.route || (destination == Destination.Chats && route == Destination.SavedMessages.route),
                            onClick = { navigate(destination) },
                            icon = { Icon(destination.icon, contentDescription = null) },
                            label = { Text(destination.label) },
                        )
                    }
                }
            },
        ) { insets ->
            NavHost(controller, startDestination = Destination.Chats.route, modifier = Modifier.padding(insets).consumeWindowInsets(insets)) {
                composable(Destination.Chats.route) { HomeScreen(onOpenMenu = openMenu) }
                composable(Destination.Contacts.route) { ContactsScreen(onOpenMenu = openMenu) }
                composable(Destination.Calls.route) { CallsScreen(onOpenMenu = openMenu) }
                composable(Destination.Profile.route) { ProfileScreen(onOpenMenu = openMenu) }
                composable(Destination.Settings.route) { SettingsScreen(onOpenMenu = openMenu) }
                composable(Destination.SavedMessages.route) {
                    HomeScreen(onOpenMenu = openMenu, initialConversationId = "saved")
                }
            }
        }
    }
}
