package com.example.getmoview.ui.screens.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeaderTexts(text: String) {
    Text(
        text = text, modifier = Modifier.padding(top = 10.dp),
        style = MaterialTheme.typography.titleLarge,
        color = if (isSystemInDarkTheme()) Color.White else Color.Black
    )
}