package com.example.getmoview.ui.screens.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start,

            modifier = Modifier.fillMaxSize()) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp)
                    .clickable(
                        enabled = true,
                        onClick = { TODO() }
                    ),
                shape = RoundedCornerShape(20.dp),
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                ) {
                    Icon(Icons.Default.Search, contentDescription = null )
                    Text(text = "Search", modifier = Modifier.padding(start = 10.dp))
                }
            }
        }
    }
}
