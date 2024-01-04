package com.example.getmoview.ui.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.use_cases.favorite.FavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.favorite.FavoriteShowsUseCase
import com.example.getmoview.ui.ui_states.FavoriteMoviesUiState
import com.example.getmoview.ui.ui_states.FavoriteShowsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    private val favoriteShowsUseCase: FavoriteShowsUseCase
) : ViewModel() {

    private val _allLocalMovies = mutableStateOf(FavoriteMoviesUiState())
    val allLocalMovies: State<FavoriteMoviesUiState> = _allLocalMovies

    private val _allLocalShows = mutableStateOf(FavoriteShowsUiState())
    val allLocalShows: State<FavoriteShowsUiState> = _allLocalShows

    init {
        getAllFavoriteMovies(true)
        getAllFavoriteShows(true)
    }

    private fun getAllFavoriteMovies(isFavorite: Boolean) {
        favoriteMoviesUseCase(isFavorite).onEach { results ->
            when (results) {
                is Resources.Success -> {
                    _allLocalMovies.value =
                        FavoriteMoviesUiState(moviesEntity = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _allLocalMovies.value =
                        FavoriteMoviesUiState(error = results.message ?: "An error occurred")
                }

                is Resources.IsLoading -> {
                    _allLocalMovies.value = FavoriteMoviesUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllFavoriteShows(isFavorite: Boolean) {
        favoriteShowsUseCase(isFavorite).onEach { results ->
            when (results) {
                is Resources.Success -> {
                    _allLocalShows.value =
                        FavoriteShowsUiState(showsEntity = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _allLocalShows.value =
                        FavoriteShowsUiState(error = results.message ?: "An error occurred")
                }

                is Resources.IsLoading -> {
                    _allLocalShows.value = FavoriteShowsUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}