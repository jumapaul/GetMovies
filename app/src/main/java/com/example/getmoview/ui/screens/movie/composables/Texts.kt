package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeaderTexts(text: String) {
    Text(
        text = text, modifier = Modifier.padding(top = 20.dp),
        style = MaterialTheme.typography.labelLarge,
        color = Color.Black
    )
}