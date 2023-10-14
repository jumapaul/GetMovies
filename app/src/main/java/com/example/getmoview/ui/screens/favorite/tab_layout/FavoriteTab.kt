package com.example.getmoview.ui.screens.favorite.tab_layout

import androidx.compose.material.TabRow
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRowDefaults
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
fun FavoriteTabs(tabs: List<FavoritesScreens>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = Color.White
    ) {
        tabs.forEachIndexed { index, favoritesScreen ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = favoritesScreen.title,
                        color = Color.Black
                    )
                },
                icon = { /*TODO*/ })
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FavoriteContent(
    tabs: List<FavoritesScreens>,
    pagerState: PagerState,
    navController: NavController
) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens(navController)
    }
}