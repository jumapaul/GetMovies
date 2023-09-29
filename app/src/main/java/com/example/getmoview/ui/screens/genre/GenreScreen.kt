package com.example.getmoview.ui.screens.genre

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.ExpandButton
import com.example.getmoview.ui.composables.ListingItem
import com.example.getmoview.ui.screens.genre.tab_layout.GenreContent
import com.example.getmoview.ui.screens.genre.tab_layout.GenreScreens
import com.example.getmoview.ui.screens.genre.tab_layout.GenreTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GenreScreen(
    navController: NavController,
    ) {

    val selectedGenreIds = remember {
        mutableStateOf(emptyList<Int>())
    }

    val genreMovieScreen = GenreScreens.GetMoviesByGenreId(selectedGenreIds)

    val genreTvShowScreen = GenreScreens.GetShowsByGenreId(selectedGenreIds)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        GenreItem(selectedGenreIds)

        val pagerState = rememberPagerState(initialPage = 0)

        val tabs = listOf(
            genreMovieScreen,
            genreTvShowScreen
        )

        GenreTab(tabs = tabs, pagerState = pagerState)
        GenreContent(tabs = tabs, pagerState = pagerState, navController = navController)

    }
}


@Composable
fun GenreItem(
    selectedGenreIds: MutableState<List<Int>>
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
            ExpandedItemList(selectedGenreIds = selectedGenreIds)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandedItemList(
    viewModel: GenreViewModel = hiltViewModel(),
    selectedGenreIds: MutableState<List<Int>>
) {
    val state = viewModel.genre.value

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        state.genre.forEach { genre ->
            ListingItem(
                genre = genre,
                selectedGenreIds = selectedGenreIds
            )
        }
    }
}
