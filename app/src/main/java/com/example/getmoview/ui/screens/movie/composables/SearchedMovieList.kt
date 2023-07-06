package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.getmoview.ui.screens.SearchedUiState

@Composable
fun SearchedMovieList(
    state: SearchedUiState
){

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(state.movies){item ->
            item.poster_path?.let {
                MovieItem(
                    posterPath = it,
                    title = item.title,
                    date = item.release_date,
                    percentage = item.vote_average.toFloat(),
                    fontSize = 15.sp
                )
            }
        }
    })

}