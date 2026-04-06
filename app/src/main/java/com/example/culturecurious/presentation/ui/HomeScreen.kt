package com.example.culturecurious.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.culturecurious.data.remote.dto.CountryDto
import com.example.culturecurious.presentation.uistate.UiState

@Composable
fun HomeScreen(uiState: UiState) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> CircularProgressIndicator(
                color = Color.Green,
                strokeWidth = 4.dp
            )

            uiState.errorMessage.isNotEmpty() -> Text(
                text = "Error: ${uiState.errorMessage}",
                color = Color.Red
            )

            uiState.countries.isNotEmpty() -> {
                CountriesList(uiState.countries)
            }
        }
    }
}

@Composable
private fun CountriesList(countries : List<CountryDto>) {

    LazyColumn {
        items(countries) { country ->
            Text("Country Name: ${country.name.common}")
        }
    }
}