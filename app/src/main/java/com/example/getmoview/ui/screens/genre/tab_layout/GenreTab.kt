package com.example.getmoview.ui.screens.genre.tab_layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun GenreTab(tabs: List<GenreScreens>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        indicator = {tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = Color.White
    ) {
        tabs.forEachIndexed { index, genreScreens ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                          scope.launch {
                              pagerState.animateScrollToPage(index)
                          }
                },
                text = { Text(text = genreScreens.title) },
                icon = { /*TODO*/ })
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun GenreContent(
    tabs: List<GenreScreens>,
    pagerState: PagerState,
    navController: NavController
){
    HorizontalPager(count = tabs.size, state = pagerState) {page ->
    tabs[page].screens(navController)
    }
}