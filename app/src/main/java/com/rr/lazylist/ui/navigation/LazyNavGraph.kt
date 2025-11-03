package com.rr.lazylist.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rr.lazylist.ui.screens.TaskScreen
import com.rr.lazylist.ui.screens.AnalyticsScreen
import com.rr.lazylist.ui.screens.ChatbotScreen
import com.rr.lazylist.ui.screens.SettingsScreen
import com.rr.lazylist.ui.screens.AchievementsScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Analytics : Screen("analytics", "Insights", Icons.Default.BarChart)
    object Chatbot : Screen("chatbot", "LazyBot", Icons.Default.Forum)
    object Achievements : Screen("achievements", "Trophies", Icons.Default.Star)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

@Composable
fun LazyNavGraph() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination
                
                listOf(
                    Screen.Home,
                    Screen.Analytics,
                    Screen.Chatbot,
                    Screen.Achievements,
                    Screen.Settings
                ).forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                TaskScreen()
            }
            
            composable(Screen.Analytics.route) {
                AnalyticsScreen()
            }
            
            composable(Screen.Chatbot.route) {
                ChatbotScreen()
            }
            
            composable(Screen.Achievements.route) {
                AchievementsScreen()
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

