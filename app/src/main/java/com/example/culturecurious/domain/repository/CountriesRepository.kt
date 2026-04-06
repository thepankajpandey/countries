package com.example.culturecurious.domain.repository

import com.example.culturecurious.data.remote.dto.CountryDto
import com.example.culturecurious.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getCountries(): Flow<NetworkResult<List<CountryDto>>>
}