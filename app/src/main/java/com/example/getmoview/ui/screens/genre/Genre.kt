package com.example.getmoview.ui.screens.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.screens.composables.ExpandButton
import com.example.getmoview.ui.screens.composables.ListingItem

@Composable
fun GenreScreen(
    viewModel: GenreViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenreItem()
        }
    }
}

@Composable
fun GenreItem(

) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "See all genre",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
            ExpandButton(expanded = expanded, onClick = {
                expanded = !expanded
            })
        }
        if (expanded) {
            ExpandedItemList()
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandedItemList(
    viewModel: GenreViewModel = hiltViewModel()
) {
    val state = viewModel.genre.value

    FlowRow(
        horizontalArrangement = Arrangement.Start
    ) {
        state.genre.forEach { genre ->
            ListingItem(text = genre.name)
        }
    }
}