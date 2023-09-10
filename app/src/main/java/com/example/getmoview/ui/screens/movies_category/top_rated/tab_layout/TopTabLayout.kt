package com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout

import androidx.compose.foundation.background
import androidx.compose.material.LeadingIconTab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MoviesShowsTab(tabs: List<MoviesShowsTabScreens>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.background(color = Color.White),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = Color.White
    ) {
        tabs.forEachIndexed { index, moviesShowsTabScreens ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = moviesShowsTabScreens.title) },
                icon = { /*TODO*/ },
                enabled = true
                )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieShowContent(
    tabs: List<MoviesShowsTabScreens>,
    pagerState: PagerState,
    navController: NavController
) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState
    ) { page ->
        tabs[page].screens(navController)
    }
}