package com.example.getmoview.ui.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem

@Composable
fun SearchedMovieItem(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit,
    onItemClick: (MovieDtoItem) -> Unit,
    searchedItem: MovieDtoItem,
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

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(height = 200.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(), shape = RoundedCornerShape(10.dp)
        ) {
            MovieRequester(posterPath = posterPath)
        }

        Box(modifier = Modifier.padding(10.dp)) {
            CircularProgressBar(
                percentage = percentage,
                radius = radius,
                fontSize = fontSize,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .GradientBackground(
                        listOf(
                            Color.Black,
                            Color.Gray,
                            Color.LightGray,
                            Color.Transparent
                        ), 90f
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                    color = Color.White
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 5.dp),
                    color = Color.White
                )
            }
        }
    }
}



