package com.example.getmoview.ui.screens.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.ui.screens.SearchedUiState
import com.example.getmoview.ui.screens.TrendingUiState
import com.example.getmoview.ui.screens.UiState
import com.example.getmoview.use_case.PopularMoviesUseCase
import com.example.getmoview.use_case.SearchUseCase
import com.example.getmoview.use_case.TopRatedMoviesUseCase
import com.example.getmoview.use_case.TrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val trendingUseCase: TrendingUseCase,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    // Popular movies and top rated
    private val _popularMovieState = mutableStateOf(UiState())
    val popularMovies: State<UiState> = _popularMovieState

    private val _topRatedMovieState = mutableStateOf(UiState())
    val topRatedMovies: State<UiState> = _topRatedMovieState

    // Trending
    private val _trendingMovieState = mutableStateOf(TrendingUiState())
    val trendingMovieState: State<TrendingUiState> = _trendingMovieState

        // Searched
    private val _searchedMovies = mutableStateOf(SearchedUiState())
    val searchedMovies: State<SearchedUiState> = _searchedMovies

    private val searchPage = 1


    init {
        getPopularMovies()
        getTopRatedMovies()
        getTrendingMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        popularMoviesUseCase().onEach { result ->

            when (result) {
                is Resources.Success -> {
                    _popularMovieState.value = UiState(movies = result.data ?: emptyList())
                }

                is Resources.Error -> {
                    _popularMovieState.value =
                        UiState(error = result.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _popularMovieState.value = UiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        topRatedMoviesUseCase().onEach { result ->
            when (result) {
                is Resources.Success -> {
                    _topRatedMovieState.value = UiState(movies = result.data ?: emptyList())

                }

                is Resources.Error -> {
                    _topRatedMovieState.value =
                        UiState(error = result.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _topRatedMovieState.value = UiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTrendingMovies() = viewModelScope.launch {
        trendingUseCase().onEach { results ->
            when (results) {

                is Resources.Success -> {
                    _trendingMovieState.value =
                        TrendingUiState(movies = results.data ?: emptyList())
                }

                is Resources.Error -> {
                    _trendingMovieState.value =
                        TrendingUiState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _trendingMovieState.value = TrendingUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSearchedMovies(searchedMovies: String) = viewModelScope.launch {
        searchUseCase(searchedMovies).onEach { results ->
            when (results) {
                is Resources.Success -> {
                    _searchedMovies.value = SearchedUiState(movies = (results.data ?: emptyList()))
                }

                is Resources.Error -> {
                    _searchedMovies.value =
                        SearchedUiState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _searchedMovies.value = SearchedUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _moviesNotFound = mutableStateOf(false)
    val moviesNotFound: State<Boolean> = _moviesNotFound

    fun moviesNotFound(notFound: Boolean){
        _moviesNotFound.value = notFound
    }
}