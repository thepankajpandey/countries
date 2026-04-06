package com.example.culturecurious.data.remote

import com.example.culturecurious.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountriesApiService {
    @GET("all?fields=name,capital,currencies")
    suspend fun getCountries(): List<CountryDto>
}