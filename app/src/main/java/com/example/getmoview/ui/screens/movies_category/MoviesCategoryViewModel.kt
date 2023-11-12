package com.example.getmoview.ui.screens.movies_category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.use_cases.popular.PopularMoviesUseCase
import com.example.getmoview.domain.use_cases.popular.PopularTvShowsUseCase
import com.example.getmoview.domain.use_cases.UpcomingMoviesUseCase
import com.example.getmoview.domain.use_cases.top_rated.TopRatedMoviesUseCase
import com.example.getmoview.domain.use_cases.top_rated.TopRatedTvShowsUseCase
import com.example.getmoview.ui.ui_states.MovieUiState
import com.example.getmoview.ui.ui_states.TvShowUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesCategoryViewModel @Inject constructor(
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase,
    private val topRatedTvShowsUseCase: TopRatedTvShowsUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val popularTvShowsUseCase: PopularTvShowsUseCase,
    private val repository: MovieRepository
) : ViewModel() {

    private val _upcomingMoviesState = mutableStateOf(MovieUiState())
    val upcomingMoviesState: State<MovieUiState> = _upcomingMoviesState

    private val _topRatedMoviesState = mutableStateOf(MovieUiState())
    val topRatedMoviesState: State<MovieUiState> = _topRatedMoviesState

    private val _topRatedShowsState = mutableStateOf(TvShowUiState())
    val topRatedShowsState: State<TvShowUiState> = _topRatedShowsState

    private val _popularMovies = mutableStateOf(MovieUiState())
    val popularMovies: State<MovieUiState> = _popularMovies

    private val _popularTvShows = mutableStateOf(TvShowUiState())
    val popularTvShows: State<TvShowUiState> = _popularTvShows

    init {
        getUpcomingMovies()
        getTopRatedMovies()
        getTopRatedShows()
        getPopularMovies()
        getPopularTvShows()
    }

    private fun getUpcomingMovies() {
        upcomingMoviesUseCase().onEach { results ->
            when (results) {
                is Resources.IsLoading -> {
                    _upcomingMoviesState.value = MovieUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _upcomingMoviesState.value = MovieUiState(movies = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _upcomingMoviesState.value = MovieUiState(error = "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() {
        topRatedMoviesUseCase().onEach { results ->
            when (results) {
                is Resources.IsLoading -> {
                    _topRatedMoviesState.value = MovieUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _topRatedMoviesState.value = MovieUiState(movies = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _topRatedMoviesState.value =
                        MovieUiState(error = results.message ?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedShows() {
        topRatedTvShowsUseCase().onEach { results ->
            when (results) {
                is Resources.IsLoading -> {
                    _topRatedShowsState.value = TvShowUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _topRatedShowsState.value = TvShowUiState(shows = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _topRatedShowsState.value =
                        TvShowUiState(error = results.message ?: "An error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        popularMoviesUseCase().onEach { results ->

            when (results) {
                is Resources.IsLoading -> {
                    _popularMovies.value = MovieUiState(isLoading = true)
                }
                is Resources.Success -> {
                    _popularMovies.value = MovieUiState(movies = results.data?: emptyList())
                }
                is Resources.Error -> {
                    _popularMovies.value = MovieUiState(error = results.message?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularTvShows() {
        popularTvShowsUseCase().onEach { results ->

            when (results) {
                is Resources.IsLoading -> {
                    _popularTvShows.value = TvShowUiState(isLoading = true)
                }
                is Resources.Success -> {
                    _popularTvShows.value = TvShowUiState(shows = results.data?: emptyList())
                }
                is Resources.Error -> {
                    _popularTvShows.value = TvShowUiState(error= results.message?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getGenreNames(genreIds: List<Int>): List<String> {
        val response = repository.getLocalGenres()

        val genreMap = response.associate { it.id to it.name }

        return genreIds.map { genreMap[it].orEmpty() }
    }
}