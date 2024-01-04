package com.example.getmoview.ui.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.use_cases.MovieUseCase
import com.example.getmoview.domain.use_cases.UpdateMoviesUseCase
import com.example.getmoview.ui.ui_states.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

    init {
        getMovies(1)
    }

    private fun getMovies(page: Int) {

        movieUseCase(page).onEach { results ->
            when (results) {

                is Resources.Success -> {
                    _state.value = MovieUiState(movies = results.data ?: emptyList())

                }

                is Resources.Error -> {
                    _state.value =
                        MovieUiState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _state.value = MovieUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateMoviesList(movie: MoviesEntity) {
        updateMoviesUseCase(movie).onEach { results ->
            when (results) {

                is Resources.Success -> {
                    _state.value = MovieUiState(movies = results.data ?: emptyList())

                }

                is Resources.Error -> {
                    _state.value =
                        MovieUiState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _state.value = MovieUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}