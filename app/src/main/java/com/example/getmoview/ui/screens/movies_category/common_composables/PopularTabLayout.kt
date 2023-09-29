package com.example.getmoview.ui.screens.movies_category.common_composables

import androidx.compose.foundation.background
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.movies_category.popular.tab_layout.PopularMoviesShowTabScreens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PopularTabLayout(tabs: List<PopularMoviesShowTabScreens>, pagerState: PagerState) {
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
        tabs.forEachIndexed { index, popularMoviesShowTabScreens ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = popularMoviesShowTabScreens.title) },
                icon = { /*TODO*/ })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PopularMoviesShowContent(
    tabs: List<PopularMoviesShowTabScreens>,
    pagerState: PagerState,
    navController: NavController
){
    HorizontalPager(count = tabs.size, state = pagerState) {page ->
    tabs[page].screens(navController)

    }
}