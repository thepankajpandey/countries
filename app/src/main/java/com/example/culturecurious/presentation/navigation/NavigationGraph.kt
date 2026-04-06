package com.example.culturecurious.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.culturecurious.presentation.ui.HomeScreen
import com.example.culturecurious.presentation.viewmodel.CountryViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ScreenHome.route
    ) {
        composable(Screen.ScreenHome.route){
            val viewModel: CountryViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(uiState)
        }
    }
}