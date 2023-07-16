package com.example.getmoview.ui.screens.movie.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.getmoview.R
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.domain.model.popular_top_rated.SearchedItem

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PopularAndTopRatedMovieItem(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit,
    onItemClick: (MovieEntity) -> Unit,
    movieEntity: MovieEntity,
    radius: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .padding(5.dp)
            .clickable { onItemClick(movieEntity) }
    ) {
        MovieItems(
            posterPath = posterPath,
            title = title,
            date = date,
            percentage = percentage,
            fontSize = fontSize,
            radius = radius
        )
    }
}

@Composable
fun TrendingMovieItem(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit,
    onItemClick: (TrendingMoviesEntity) -> Unit,
    trendingMoviesEntity: TrendingMoviesEntity,
    radius: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .padding(5.dp)
            .clickable { onItemClick(trendingMoviesEntity) }
    ) {
        MovieItems(
            posterPath = posterPath,
            title = title,
            date = date,
            percentage = percentage,
            fontSize = fontSize,
            radius = radius
        )
    }
}

@Composable
fun SearchedMovieItem(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit,
    onItemClick: (SearchedItem) -> Unit,
    searchedItem: SearchedItem,
    radius: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .padding(5.dp)
            .clickable { onItemClick(searchedItem) }
    ) {
        MovieItems(
            posterPath = posterPath,
            title = title,
            date = date,
            percentage = percentage,
            fontSize = fontSize,
            radius = radius
        )
    }
}

@Composable
fun MovieItems(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit,
    radius: Dp
) {
    Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(10.dp)) {
        MovieRequester(posterPath = posterPath)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 5.dp),
        contentAlignment = Alignment.TopStart
    ) {
        CircularProgressBar(percentage = percentage, radius = radius, fontSize = fontSize)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                color = Color.White
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                color = Color.White
            )
        }
    }
}


