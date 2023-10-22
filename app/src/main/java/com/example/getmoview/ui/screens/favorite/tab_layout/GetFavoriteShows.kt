package com.example.getmoview.ui.screens.favorite.tab_layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.favorite.FavoriteMoviesViewModel
import com.example.getmoview.ui.screens.favorite.NoFavorite
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetFavoriteShows(
    navController: NavController,
    viewModel: FavoriteMoviesViewModel = hiltViewModel()
) {
    val favoriteShows = viewModel.allLocalShows.value

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        if (favoriteShows.showsEntity.isEmpty()){
            NoFavorite(text = "No favorite shows")
        }else{
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(favoriteShows.showsEntity) { shows ->

                    val names = remember {
                        mutableStateOf<List<String>>(emptyList())
                    }

                    Box(modifier = Modifier.clickable {
                        navController.navigate(BottomNavigationRoutes.ShowsDetail.routes + "/${shows.id}")
                    }) {
                        VerticalMoviesItem(
                            posterPath = shows.poster_path,
                            title = shows.name,
                            description = shows.overview,
                            date = shows.first_air_date,
                            onClick = {viewModel.addShows(shows)},
                            favorite = null,
                            genreId = names
                        )
                    }
                }
            }
        }
    }
}