package com.example.getmoview.ui.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesItem(
    category: String
) {

    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .background(Color.LightGray, RoundedCornerShape(5.dp))
    ) {
        Text(
            text = category, style = MaterialTheme.typography.bodyMedium,
            fontFamily = FontFamily.SansSerif,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.padding(5.dp)
        )
    }
}