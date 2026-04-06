package com.example.culturecurious.di

import com.example.culturecurious.data.remote.CountriesApiService
import com.example.culturecurious.data.repository.CountriesRepositoryImpl
import com.example.culturecurious.domain.repository.CountriesRepository
import com.example.culturecurious.util.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): CountriesApiService {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApiService::class.java)
    }

    @Provides
    fun provideRepository(api: CountriesApiService): CountriesRepository {
        return CountriesRepositoryImpl(api)
    }
}