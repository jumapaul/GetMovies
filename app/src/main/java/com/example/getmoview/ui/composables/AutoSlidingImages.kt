package com.example.getmoview.ui.composables

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSliding(
    modifier: Modifier,
    slideDuration: Long = 5000L,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    LaunchedEffect(pagerState.currentPage) {
        delay(slideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % 20)
    }

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

    }

}