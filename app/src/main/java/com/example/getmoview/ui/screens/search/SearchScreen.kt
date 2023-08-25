package com.example.getmoview.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.SearchBar
import com.example.getmoview.ui.composables.SearchedMovieList
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchedMovies = searchViewModel.searchState.value
    val searchTerm = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        LaunchedEffect(searchTerm.value){
            delay(1000)
            searchViewModel.getSearchMovie(searchTerm.value)
        }

        SearchBar(searchTerm = searchTerm)

        SearchedMovieList(state = searchedMovies, navController = navController)
    }
}