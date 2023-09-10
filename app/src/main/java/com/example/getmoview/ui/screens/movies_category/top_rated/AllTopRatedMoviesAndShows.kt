package com.example.getmoview.ui.screens.movies_category.top_rated

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.movies_category.common_composables.MoviesAndShowsTitle
import com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout.MovieShowContent
import com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout.MoviesShowsTab
import com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout.MoviesShowsTabScreens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AllTopRatedAndTvShows(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        MoviesAndShowsTitle(navController = navController, title = "Top Rated")
        val pagerState = rememberPagerState(initialPage = 0)

        val tabs = listOf(
            MoviesShowsTabScreens.TopMovies,
            MoviesShowsTabScreens.TopShows
        )

        MoviesShowsTab(tabs = tabs, pagerState = pagerState)
        MovieShowContent(tabs = tabs, pagerState = pagerState, navController)
    }
}