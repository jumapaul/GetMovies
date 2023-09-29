package com.example.getmoview.ui.screens.genre

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.ui.ui_states.GenreUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase,
    private val repository: MovieRepository
) : ViewModel() {
    private val _genre = mutableStateOf(GenreUiState())
    val genre: State<GenreUiState> = _genre

    init {
        getGenres()
    }

    private fun getGenres() {
        genreUseCase().onEach { result ->
            when (result) {
                is Resources.Success -> {
                    _genre.value = GenreUiState(genre = result.data ?: emptyList())
                }

                is Resources.IsLoading -> {
                    _genre.value = GenreUiState(isLoading = true)
                }

                is Resources.Error -> {
                    _genre.value =
                        GenreUiState(error = result.message ?: "Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}