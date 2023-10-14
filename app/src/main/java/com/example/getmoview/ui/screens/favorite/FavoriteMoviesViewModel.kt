package com.example.getmoview.ui.screens.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.use_cases.local.local_movie_use_case.AddFavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.local.local_movie_use_case.DeleteFavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.local.local_movie_use_case.GetFavoriteMovieByIdUseCase
import com.example.getmoview.domain.use_cases.local.local_movie_use_case.GetFavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.local.local_shows_use_case.AddFavoriteShowUseCase
import com.example.getmoview.domain.use_cases.local.local_shows_use_case.DeleteFavoriteShowsUseCase
import com.example.getmoview.domain.use_cases.local.local_shows_use_case.GetFavoriteShowByIdUseCase
import com.example.getmoview.domain.use_cases.local.local_shows_use_case.GetFavoriteShowsUseCase
import com.example.getmoview.ui.ui_states.FavoriteMoviesUiState
import com.example.getmoview.ui.ui_states.FavoriteShowsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase,
    private val deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase,
    private val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase,
    private val addFavoriteShowUseCase: AddFavoriteShowUseCase,
    private val deleteFavoriteShowsUseCase: DeleteFavoriteShowsUseCase,
    private val getFavoriteShowByIdUseCase: GetFavoriteShowByIdUseCase,
    private val getFavoriteShowsUseCase: GetFavoriteShowsUseCase
) : ViewModel() {

    private val _allLocalMovies = mutableStateOf(FavoriteMoviesUiState())
    val allLocalMovies: State<FavoriteMoviesUiState> = _allLocalMovies

    private val _allLocalShows = mutableStateOf(FavoriteShowsUiState())
    val allLocalShows: State<FavoriteShowsUiState> = _allLocalShows

    private val favoriteMoviesId = mutableSetOf<Int>()

    init {
        getAllMovies()
        getAllShows()
    }

    private fun getAllMovies() = viewModelScope.launch {
        getFavoriteMoviesUseCase().onEach { results ->
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

    fun getMovieById(id: Int) = viewModelScope.launch {
        getFavoriteMovieByIdUseCase(id)
    }

    fun addFavoriteMovie(moviesEntity: MoviesEntity) = viewModelScope.launch {
        addFavoriteMoviesUseCase(moviesEntity)
    }

    fun deleteFavoriteMovie(moviesEntity: MoviesEntity) = viewModelScope.launch {
        deleteFavoriteMoviesUseCase(moviesEntity)
    }


//    suspend fun toggleFavoriteMoviesStatus(moviesEntity: MoviesEntity) {
//        if (isMovieFavorite(moviesEntity.id)) {
//            deleteFavoriteMoviesUseCase(moviesEntity)
//
//        } else {
//            addFavoriteMoviesUseCase(moviesEntity)
//        }
//    }

    fun addShows(showsEntity: ShowsEntity) = viewModelScope.launch {
        addFavoriteShowUseCase(showsEntity)
    }

    private fun getAllShows() = viewModelScope.launch {
        getFavoriteShowsUseCase().onEach { results ->
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


    suspend fun isMovieFavorite(movieId: Int): Boolean {
        return true
    }
}