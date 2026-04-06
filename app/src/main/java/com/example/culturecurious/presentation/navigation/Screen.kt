package com.example.culturecurious.presentation.navigation

sealed class Screen(val route: String, val label: String) {
    object ScreenHome : Screen("screen_home", "Home")
}