package com.example.culturecurious.data.repository

import com.example.culturecurious.data.remote.CountriesApiService
import com.example.culturecurious.data.remote.dto.CountryDto
import com.example.culturecurious.domain.repository.CountriesRepository
import com.example.culturecurious.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val apiService: CountriesApiService) :
    CountriesRepository {
    override suspend fun getCountries(): Flow<NetworkResult<List<CountryDto>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = apiService.getCountries()
            emit(NetworkResult.Success(response))
        } catch (e: Exception) {
            emit(NetworkResult.Error("Failed to fetch countries", e))
        }
    }
}