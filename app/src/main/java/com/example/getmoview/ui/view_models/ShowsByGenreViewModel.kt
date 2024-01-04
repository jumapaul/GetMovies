package com.example.getmoview.ui.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.use_cases.shows.ShowsUseCase
import com.example.getmoview.domain.use_cases.shows.UpdateShowsUseCase
import com.example.getmoview.ui.ui_states.TvShowUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowsByGenreViewModel @Inject constructor(
    private val showsUseCase: ShowsUseCase,
    private val updateShowsUseCase: UpdateShowsUseCase
) : ViewModel() {
    private val _tvShows = mutableStateOf(TvShowUiState())
    val tvShows: State<TvShowUiState> = _tvShows

    init {
        getShows()
    }

    private fun getShows() {
        showsUseCase().onEach { shows ->
            when (shows) {
                is Resources.IsLoading -> {
                    _tvShows.value = TvShowUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _tvShows.value = TvShowUiState(shows = shows.data ?: emptyList())
                }

                is Resources.Error -> {
                    _tvShows.value =
                        TvShowUiState(error = shows.message ?: "Unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }

    fun updateShowsList(showsEntity: ShowsEntity) {
        updateShowsUseCase(showsEntity).onEach { results ->
            when (results) {
                is Resources.IsLoading -> {
                    _tvShows.value = TvShowUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _tvShows.value = TvShowUiState(shows = results.data?: emptyList())
                }

                is Resources.Error -> {
                    _tvShows.value = TvShowUiState(
                        error = results.message?: "Unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}