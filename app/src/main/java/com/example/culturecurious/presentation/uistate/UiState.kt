package com.example.culturecurious.presentation.uistate

import com.example.culturecurious.data.remote.dto.CountryDto

data class UiState(
    val isLoading: Boolean = false,
    val countries: List<CountryDto> = emptyList(),
    val errorMessage: String = ""
)