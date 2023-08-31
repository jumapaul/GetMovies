package com.example.getmoview.ui.screens.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.domain.paginator.DefaultPaginator
import com.example.getmoview.ui.ui_states.MovieUiState
import com.example.getmoview.ui.screens.movie_detail.SearchMovieState
import com.example.getmoview.ui.screens.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

    // Searched
    private val _searchedMovies = mutableStateOf(SearchMovieState())
    val searchedMovies: State<SearchMovieState> = _searchedMovies

//    init {
//        getMovies()
//    }

    private val paginator = DefaultPaginator(
        initialKeys = _state.value.page,
        onLoadUpdate = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            movieUseCase(nextPage)


        },
        getNextKey = {
            _state.value.page + 1
        },
        onError = {
            _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { movies, newKey ->
            _state.value = _state.value.copy(
                movies = _state.value.movies + movies,
                page = newKey,
                endReached = movies.isEmpty()
            )
        }
    )

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            if (!_state.value.endReached) {
                delay(2000L)
                paginator.loadNextItem()
            }
        }
    }

//    private fun getMovies() {
//        movieUseCase(moviesPage).onEach { results ->
//            when (results) {
//                is Resources.IsLoading -> {
//                    _movies.value = UiState(isLoading = true)
//                }
//
//                is Resources.Success -> {
//                    _movies.value = UiState(movies = results.data ?: emptyList())
//
//                }
//
//                is Resources.Error -> {
//                    _movies.value = UiState(error = results.message ?: "Unexpected error occurred")
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}