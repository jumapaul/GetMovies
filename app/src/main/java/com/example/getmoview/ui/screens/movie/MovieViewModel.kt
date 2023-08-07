package com.example.getmoview.ui.screens.movie

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.MovieRepository
import com.example.getmoview.domain.model.DefaultPaginator
import com.example.getmoview.ui.screens.UiState
import com.example.getmoview.ui.screens.movie_detail.SearchMovieState
import com.example.getmoview.use_case.MovieUseCase
import com.example.getmoview.use_case.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(UiState())
    val state: State<UiState> = _state

    // Searched
    private val _searchedMovies = mutableStateOf(SearchMovieState())
    val searchedMovies: State<SearchMovieState> = _searchedMovies

    init {
//        getMovies()
    }

    private val paginator = DefaultPaginator(
        initialKeys = _state.value.page,
        onLoadUpdate = {
            _state.value = _state.value.copy(isLoading = it)

            Log.d("===========>", "message: ${_state.value.page}")
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
            if (!_state.value.endReached){
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

    fun getSearchedMovies(searchedMovies: String){
        searchUseCase(searchedMovies).onEach { results ->
            when (results) {
                is Resources.Success -> {
                    _searchedMovies.value = SearchMovieState(movie = (results.data ?: emptyList()))

                    if (results.data?.isEmpty() == true){
                        moviesNotFound(true)
                    }
                }

                is Resources.Error -> {
                    _searchedMovies.value =
                        SearchMovieState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _searchedMovies.value = SearchMovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _moviesNotFound = mutableStateOf(false)
    val moviesNotFound: State<Boolean> = _moviesNotFound

    fun moviesNotFound(notFound: Boolean) {
        _moviesNotFound.value = notFound
    }

}