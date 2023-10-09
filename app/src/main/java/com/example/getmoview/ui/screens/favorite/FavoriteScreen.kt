package com.example.getmoview.ui.screens.favorite

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.composables.MovieItems
import com.example.getmoview.ui.composables.MyTexts

@Composable
fun FavoriteScreen(
    viewModel: FavoriteMoviesViewModel = hiltViewModel()
) {

    val favoriteMovies = viewModel.allLocalMovies.value

    Log.d("---------> Local movies", "FavoriteScreen: ${favoriteMovies.moviesEntity.size}")

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            Text(
                text = "Favorite",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(modifier = Modifier){
            items(favoriteMovies.moviesEntity){movies->
                MovieItems(
                    posterPath = movies.poster_path,
                    title = movies.title,
                    date = movies.release_date,
                    percentage = movies.vote_average.toFloat(),
                    fontSize = 18.sp,
                    radius = 18.dp,
                    modifier = Modifier
                )
            }
        }
    }
}