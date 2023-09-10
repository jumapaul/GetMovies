package com.example.getmoview.ui.screens.movies_category.common_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun MoviesAndShowsTitle(
    navController: NavController,
    title: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = null,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}