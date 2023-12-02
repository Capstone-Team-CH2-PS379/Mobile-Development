package com.capstone.fluentin.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ngobrol : Screen("ngobrol")
    object Leaderboard : Screen("leaderboard")
    object Profile : Screen("profile")
}