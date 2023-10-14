package com.example.getmoview.ui.screens.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.ui.screens.movie_detail.SearchMovieState
import com.example.getmoview.domain.use_cases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchState = mutableStateOf(SearchMovieState())
    val searchState: State<SearchMovieState> = _searchState


    fun getSearchMovie(searchString: String) {
        searchUseCase(searchString).onEach { results ->

            when (results) {
                is Resources.Success -> {
                    _searchState.value = SearchMovieState(movie = results.data ?: emptyList())

                    if (results.data?.isEmpty() == true) {
                        moviesNotFound(true)
                    }
                }

                is Resources.Error -> {
                    _searchState.value =
                        SearchMovieState(error = results.message ?: "Unexpected error occurred")
                }

                is Resources.IsLoading -> {
                    _searchState.value = SearchMovieState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    private val _moviesNotFound = mutableStateOf(false)
    val moviesNotFound: State<Boolean> = _moviesNotFound
    private fun moviesNotFound(notFound: Boolean) {
        _moviesNotFound.value = notFound
    }

}