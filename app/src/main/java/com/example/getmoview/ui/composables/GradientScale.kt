package com.example.getmoview.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        // Setting angle in radians
        val angleRad = angle / 180 * PI

        // Getting the sine and cosine
        // Cosine for the x axis and sine for y
        val cosineAngle = cos(angleRad).toFloat()
        val sineAngle = sin(angleRad).toFloat()

        //Setting the radius and the offset
        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(cosineAngle * radius, sineAngle * radius)

        val cosineAngle2 = min(offset.x.coerceAtLeast(0f), size.width)
        val sineAngle2 = size.height - min(offset.y.coerceAtLeast(0f), size.height)

        //Setting the exact offset
        val exactOffset = Offset(cosineAngle2, sineAngle2)

        //Draw rectangle with the above values
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ), size = size
        )
    }
)