package com.example.getmoview.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun VerticalMoviesItem(
    posterPath: String,
    title: String,
    description: String,
    date: String,
    onClick: () -> Unit,
    isFavorite: Boolean,
    genreId: MutableState<List<String>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {

        Card(
            modifier = Modifier
                .fillMaxHeight()
                .width(150.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            MovieRequester(posterPath = posterPath)
        }

        Column(modifier = Modifier.padding(start = 5.dp)) {
            MyTexts(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 5.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            val favorite: ImageVector =
                if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    MyTexts(
                        text = date,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                    )

                    Icon(imageVector = favorite, contentDescription = null,
                        modifier = Modifier.clickable {
                            onClick()
                        }
                    )

                }

//                FavoriteStar(
//                    onClick = {},
//                    size = 25.dp
//
//                )

            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(genreId.value) { genreId ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(shape = CircleShape)
                                .background(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
                        )
                        MyTexts(
                            text = genreId,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}