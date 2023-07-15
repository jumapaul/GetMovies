package com.example.getmoview.ui.screens.movie_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Constants.POP_AND_TOP_ID
import com.example.getmoview.common.Constants.TRENDING_ID
import com.example.getmoview.common.Resources
import com.example.getmoview.use_case.PopularAndTopRatedMovieDetailUseCase
import com.example.getmoview.use_case.TrendingMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val popularAndTopRatedMovieDetailUseCase: PopularAndTopRatedMovieDetailUseCase,
    private val trendingMovieDetailUseCase: TrendingMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _popularAndTopRatedMovieState =
        mutableStateOf(PopularAndTopRatedMovieState())
    val popularAndTopRatedMovieState: State<PopularAndTopRatedMovieState> =
        _popularAndTopRatedMovieState

    private val _trendingMovieState = mutableStateOf(TrendingMovieState())
    val trendingMovieState: State<TrendingMovieState> = _trendingMovieState

    init {
        savedStateHandle.get<String>(POP_AND_TOP_ID)?.let { movieId ->
            getPopularAndTopRatedId(movieId.toInt() )
        }

//        savedStateHandle.get<String>(TRENDING_ID)?.let { movieId->
//            Log.d("---------->", "here: ${movieId.toInt()}")
//            getTrendingMovieId(movieId.toInt() )
//        }

    }

    private fun getPopularAndTopRatedId(movieId: Int) {
        popularAndTopRatedMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resources.IsLoading -> {
                    _popularAndTopRatedMovieState.value =
                        PopularAndTopRatedMovieState(isLoading = true)
                }

                is Resources.Success -> {
                    _popularAndTopRatedMovieState.value =
                        PopularAndTopRatedMovieState(movie = result.data)
                }

                is Resources.Error -> {
                    _popularAndTopRatedMovieState.value =
                        PopularAndTopRatedMovieState(
                            error = result.message ?: "Unexpected error occurred"
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTrendingMovieId(movieId: Int) {
        trendingMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resources.IsLoading -> {
                    _trendingMovieState.value = TrendingMovieState(isLoading = true)
                }

                is Resources.Success -> {
                    _trendingMovieState.value = TrendingMovieState(movie = result.data)
                }

                is Resources.Error -> {
                    _trendingMovieState.value =
                        TrendingMovieState(error = result.message ?: "Unexpected error occurred")
                }
            }
        }
    }
}