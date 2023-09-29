package com.example.getmoview.ui.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyTexts(
    text: String,
    style: TextStyle,
    color: Color = if (isSystemInDarkTheme()) Color.White else Color.Black,
    modifier: Modifier
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}