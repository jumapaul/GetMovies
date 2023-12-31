package com.example.getmoview.ui.screens.movie_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.ui.composables.CircularProgressBar
import com.example.getmoview.ui.composables.gradientBackground
import com.example.getmoview.ui.composables.MovieRequester
import com.example.getmoview.ui.composables.MyTexts
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.view_models.MovieDetailViewModel
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel
import com.example.getmoview.ui.view_models.ShowsByGenreViewModel

@Composable
fun ShowsDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    showsByGenreViewModel: ShowsByGenreViewModel = hiltViewModel()
) {
    val shows = viewModel.showState.value

    var isFavorite by remember {
        mutableStateOf(false)
    }

    shows.shows?.let { showsId ->
        ShowDetailScreen(
            navController = navController,
            posterPath = showsId.poster_path,
            percentage = showsId.vote_average,
            title = showsId.name,
            releaseDate = showsId.first_air_date,
            overview = showsId.overview,
            showsEntity = showsId,
            onClick = {
                var showsItem = shows.shows
                showsItem.isFavorite = !isFavorite
                isFavorite = showsItem.isFavorite

                showsByGenreViewModel.updateShowsList(showsItem)
            },
            isFavorite = isFavorite
        )
    }
}

@Composable
fun ShowDetailScreen(
    navController: NavController,
    posterPath: String,
    percentage: Double,
    title: String,
    releaseDate: String,
    overview: String,
    showsEntity: ShowsEntity,
    onClick: () -> Unit,
    isFavorite: Boolean
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        MovieRequester(posterPath = posterPath)
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.wrapContentSize(),
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .gradientBackground(
                                listOf(
                                    Color.Gray,
                                    Color.LightGray,
                                    Color.Transparent
                                ), 90f
                            ),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Column(Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {

                                    CircularProgressBar(
                                        percentage = percentage.toFloat(),
                                        fontSize = 15.sp,
                                        radius = 30.dp
                                    )


                                    Text(
                                        text = "User\n Score",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                FavoriteShows(
                                    onClick = {
                                        onClick()
                                    },
                                    size = 50.dp,
                                    isFavorite = isFavorite
                                )
                            }

                            MyTexts(
                                text = title,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(top = 15.dp)
                            )

                            MyTexts(
                                text = releaseDate,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 5.dp)
                            )


//                            LazyRow(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 10.dp),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//
//                                items(genres.value) { genres ->
//                                    Cards(text = genres)
//                                }
//
//                            }

                            Text(
                                text = overview,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteShows(
    onClick: () -> Unit,
    size: Dp,
    isFavorite: Boolean
) {
    val favorite: ImageVector =
        if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Icon(
        modifier = Modifier
            .clickable {
                onClick
            }
            .size(size),
        contentDescription = null,
        imageVector = favorite,
    )
}