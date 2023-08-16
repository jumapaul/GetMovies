package com.example.getmoview.ui.screens.movies_category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.getmoview.ui.screens.composables.ExpandButton
import com.example.getmoview.ui.screens.composables.ListingItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoviesCategoryScreen() {
    val listOfCategories = listOf(
        "Popular", "Trending", "Upcoming", "Top Rated"
    )

    var expand by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "See all")
            ExpandButton(expanded = expand, onClick = { expand = !expand })
        }

        if (expand) {
            FlowRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                listOfCategories.forEach { categories ->
                    ListingItem(text = categories)
                }
            }
        }
    }
}