package com.mz.blackink.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mz.blackink.feature.home.HomeScreen

@Composable
fun BlackInkNavGraph() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "home") {
        composable("home") { HomeScreen() }
    }
}
