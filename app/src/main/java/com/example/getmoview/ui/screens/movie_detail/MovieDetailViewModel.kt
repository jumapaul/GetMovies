package com.example.getmoview.ui.screens.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Constants.MOVIES_ID
import com.example.getmoview.common.Constants.SHOWS_ID
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.use_cases.MovieDetailUseCase
import com.example.getmoview.domain.use_cases.TvShowDetailUseCase
import com.example.getmoview.domain.use_cases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val tvShowDetailUseCase: TvShowDetailUseCase,
    private val searchUseCase: SearchUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _moviesState = mutableStateOf(MoviesState())
    val moviesState: State<MoviesState> = _moviesState

    private val _showState = mutableStateOf(TvShowsState())
    val showState: State<TvShowsState> = _showState

//    private val _trendingMovieState = mutableStateOf(TrendingMovieState())
//    val trendingMovieState: State<TrendingMovieState> = _trendingMovieState

    init {
        savedStateHandle.get<String>(MOVIES_ID)?.let { movieId ->
            getPopularAndTopRatedId(movieId.toInt())
        }

        savedStateHandle.get<String>(SHOWS_ID)?.let { showsId->
            getTvShowId(showsId.toInt())
        }
//        savedStateHandle.get<String>(TRENDING_ID)?.let { movieId ->
////            getTrendingMovieId(movieId.toInt() )
//        }

    }

    private fun getPopularAndTopRatedId(movieId: Int) {
        movieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resources.IsLoading -> {
                    _moviesState.value =
                        MoviesState(isLoading = true)
                }

                is Resources.Success -> {
                    _moviesState.value =
                        MoviesState(movie = result.data)
                }

                is Resources.Error -> {
                    _moviesState.value =
                        MoviesState(
                            error = result.message ?: "Unexpected error occurred"
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTvShowId(showId: Int) {
        tvShowDetailUseCase(showId).onEach { shows ->
            when (shows) {
                is Resources.IsLoading -> {
                    _showState.value = TvShowsState(isLoading = true)
                }

                is Resources.Success -> {
                    _showState.value = TvShowsState(shows = shows.data)
                }

                is Resources.Error -> {
                    _showState.value =
                        TvShowsState(error = shows.message ?: "Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

//    private fun getTrendingMovieId(movieId: Int) {
//        trendingMovieDetailUseCase(movieId).onEach { result ->
//            when (result) {
//                is Resources.IsLoading -> {
//                    _trendingMovieState.value = TrendingMovieState(isLoading = true)
//                }
//
//                is Resources.Success -> {
//                    _trendingMovieState.value = TrendingMovieState(movie = result.data)
//                }
//
//                is Resources.Error -> {
//                    _trendingMovieState.value =
//                        TrendingMovieState(error = result.message ?: "Unexpected error occurred")
//                }
//
//            }
//        }.launchIn(viewModelScope)
//    }

//    private suspend fun getSearchedMovieId(searchQuery:String) {
//        searchUseCase(searchQuery).onEach { result ->
//            when (result) {
//                is Resources.IsLoading -> {
//                    _searchMovieState.value =
//                        SearchMovieState(isLoading = true)
//                }
//
//                is Resources.Success -> {
//                    _searchMovieState.value =
//                        SearchMovieState(movie = result.data)
//                }
//
//                is Resources.Error -> {
//                    _popularAndTopRatedMovieState.value =
//                        PopularAndTopRatedMovieState(
//                            error = result.message ?: "Unexpected error occurred"
//                        )
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

}