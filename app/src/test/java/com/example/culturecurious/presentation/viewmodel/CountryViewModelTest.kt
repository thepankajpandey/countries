package com.example.culturecurious.presentation.viewmodel

import com.example.culturecurious.data.remote.dto.CountryDto
import com.example.culturecurious.domain.repository.CountriesRepository
import com.example.culturecurious.util.NetworkResult
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {
    private lateinit var viewModel: CountryViewModel
    private val repository: CountriesRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `when repository emits loading then uiState should be loading`() = runTest {
        val flow = flow { emit(NetworkResult.Loading) }

        coEvery { repository.getCountries() } returns flow

        viewModel = CountryViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        Assert.assertTrue(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `when repository emits success then uiState should have data`() = runTest {
        // Given
        /*val countries = listOf(
            CountryDto(null,"India"),
            Country("USA")
        )*/
    }

    @Test
    fun `when repository emits error then uiState should have error message`() = runTest {
        // Given
        val errorMessage = "Something went wrong"

        val flow = flow {
            emit(NetworkResult.Error(errorMessage))
        }

        coEvery { repository.getCountries() } returns flow

        // When
        viewModel = CountryViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(errorMessage, state.errorMessage)
    }
}