package com.example.getmoview.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel

@Composable
fun GenreIdToName(
    genres: List<Int>,
    names: MutableState<List<String>>,
    viewModel: MoviesCategoryViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = genres) {
        val genreName = viewModel.getGenreNames(genres)
        names.value = genreName
    }
}