package com.example.getmoview.ui.screens.movie_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.getmoview.R
import com.example.getmoview.ui.screens.composables.Cards
import com.example.getmoview.ui.screens.composables.CircularProgressBar
import com.example.getmoview.ui.screens.composables.GradientBackground
import com.example.getmoview.ui.screens.composables.MovieRequester
import com.example.getmoview.ui.screens.composables.MyTexts
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val movie = viewModel.popularAndTopRatedMovieState.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        movie.movie?.poster_path?.let { MovieRequester(posterPath = it) }

        movie.movie?.poster_path?.let {
                MovieRequester(posterPath = it)
        }



        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.wrapContentSize(),
            ) {
                Card(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .size(25.dp, 25.dp)
                        .padding(start = 10.dp, top = 10.dp)
                        .clickable {
                            navController.navigate(BottomNavigationRoutes.MovieScreen.routes)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .GradientBackground(
                                listOf(
                                    Color.Black,
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
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                        movie.movie?.vote_average?.toFloat()?.let {
                                            CircularProgressBar(
                                                percentage = it,
                                                fontSize = 15.sp,
                                                radius = 30.dp
                                            )
                                        }

                                        MyTexts(
                                            text = "User\n Score",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                }

                                FavoriteStar(
                                    onClick = {

                                    }
                                )
                            }

                            movie.movie?.let {
                                MyTexts(
                                    text = it.title,
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier.padding(top = 15.dp)
                                )

                                MyTexts(
                                    text = it.release_date,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Cards(text = "Action")
                                Cards(text = "Adventure")
                                Cards(text = "Animation")
                                Cards(text = "Science Fiction")
                            }

                            movie.movie?.let {
                                MyTexts(
                                    text = it.overview,
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
}

@Composable
fun FavoriteStar(
    onClick: () -> Unit
) {

    var clicked by remember {
        mutableStateOf(false)
    }

    val painter: Painter = if (clicked) {
        painterResource(id = R.drawable.baseline_star_rate_24)
    } else {
        painterResource(id = R.drawable.baseline_star_border_24)
    }

    Image(
        modifier = Modifier
            .size(40.dp, 40.dp)
            .clickable {
                clicked = !clicked
                onClick()
            },
        contentDescription = null,
        painter = painter,
        contentScale = ContentScale.Crop,
    )
}