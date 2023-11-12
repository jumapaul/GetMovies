package com.example.getmoview.ui.screens.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.use_cases.MovieUseCase
import com.example.getmoview.ui.ui_states.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

//    init {
//        getMovies()
//    }

//    private val paginator = DefaultPaginator(
//        initialKeys = _state.value.page,
//        onLoadUpdate = {
//            _state.value = _state.value.copy(isLoading = it)
//        },
//        onRequest = { nextPage ->
//            movieUseCase(nextPage)
//
//
//        },
//        getNextKey = {
//            _state.value.page + 1
//        },
//        onError = {
//            it?.localizedMessage?.let { it1 -> _state.value.copy(error = it1) }
//        },
//        onSuccess = { movies, newKey ->
//            _state.value = _state.value.copy(
//                movies = _state.value.movies + movies,
//                page = newKey,
//                endReached = movies.isEmpty()
//            )
//        }
//    )

    init {
        getMovies(1)
//        loadMovies()
    }

//    fun loadMovies() {
//        viewModelScope.launch {
//            if (!_state.value.endReached) {
//                delay(2000L)
//                paginator.loadNextItem()
//            }
//        }
//    }

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

}