package com.example.getmoview.ui.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListingItem(text: String) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .wrapContentSize()
            .background(
                color = if (isSystemInDarkTheme()) Color.White else Color.Gray,
                RoundedCornerShape(5.dp)
            )

    ) {
        Text(text = text, modifier = Modifier.padding(5.dp))
    }
}