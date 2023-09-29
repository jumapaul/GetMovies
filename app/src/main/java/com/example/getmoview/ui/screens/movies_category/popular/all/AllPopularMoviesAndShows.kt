package com.example.getmoview.ui.screens.movies_category.popular.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.movies_category.common_composables.MoviesAndShowsTitle
import com.example.getmoview.ui.screens.movies_category.common_composables.PopularMoviesShowContent
import com.example.getmoview.ui.screens.movies_category.popular.tab_layout.PopularMoviesShowTabScreens
import com.example.getmoview.ui.screens.movies_category.common_composables.PopularTabLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AllPopularMoviesAndTvShow(
    navController: NavController
){
   Column(
       modifier = Modifier
           .padding(5.dp)
           .fillMaxSize(),
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.Start
   ) {
       MoviesAndShowsTitle(navController = navController, title = "Popular")

       val pagerState = rememberPagerState(initialPage = 0)
       val tabs = listOf(
           PopularMoviesShowTabScreens.PopularMovies,
           PopularMoviesShowTabScreens.PopularShows
       )
       PopularTabLayout(tabs = tabs, pagerState = pagerState)
       PopularMoviesShowContent(tabs = tabs, pagerState = pagerState, navController = navController)
   }
}