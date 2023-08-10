package com.example.getmoview.ui.screens.movie.tab_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabScreens>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        Modifier.background(color = Color.White),
        indicator = { tabsPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabsPositions)
        }
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            content = {
                items(tabs.size) { index ->
                    Box(
                        modifier = Modifier.padding(end = 3.dp, top = 5.dp)
                            .wrapContentSize()
                            .background(color = Color.Gray, RoundedCornerShape(5.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        LeadingIconTab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = { Text(text = tabs[index].title) },
                            icon = { /*TODO*/ },
                            selectedContentColor = Color.Green,
                            unselectedContentColor = Color.Yellow,
                            enabled = true
                        )
                    }
                }
            }
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabScreens>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens()
    }
}