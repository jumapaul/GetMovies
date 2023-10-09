package com.example.getmoview.ui.screens.favorite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.use_cases.local_use_case.AddFavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.local_use_case.DeleteFavoriteMoviesUseCase
import com.example.getmoview.domain.use_cases.local_use_case.GetFavoriteMovieByIdUseCase
import com.example.getmoview.domain.use_cases.local_use_case.GetFavoriteMoviesUseCase
import com.example.getmoview.ui.ui_states.FavoriteMoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase,
    private val deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase,
    private val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase
) : ViewModel() {

    private val _allLocalMovies = mutableStateOf(FavoriteMoviesUiState())
    val allLocalMovies: State<FavoriteMoviesUiState> = _allLocalMovies

    private val favoriteMoviesId = mutableSetOf<Int>()

    init {
       getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch {

        Log.d("------ViewModel", "getAllMovies: ${_allLocalMovies.value.moviesEntity.size}")
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

    suspend fun toggleFavoriteMoviesStatus(moviesEntity: MoviesEntity) {
        if (isMovieFavorite(moviesEntity.id)) {
            deleteFavoriteMoviesUseCase(moviesEntity)

        } else {
            addFavoriteMoviesUseCase(moviesEntity)
        }
    }


    suspend fun isMovieFavorite(movieId: Int): Boolean{
        return true
    }
}