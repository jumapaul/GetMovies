package com.example.getmoview.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun MyTexts(
    text: String,
    style: TextStyle,
    color: Color = Color.White,
    modifier: Modifier
){
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
    )
}