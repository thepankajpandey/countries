package com.example.culturecurious.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.culturecurious.domain.repository.CountriesRepository
import com.example.culturecurious.presentation.uistate.UiState
import com.example.culturecurious.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState = _uiState

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            repository.getCountries().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.value = UiState(errorMessage = result.message)
                    }

                    NetworkResult.Loading -> {
                        _uiState.value = UiState(isLoading = true)
                    }

                    is NetworkResult.Success -> {
                        _uiState.value = UiState(countries = result.data)
                    }
                }

            }
        }
    }
}