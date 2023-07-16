package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    percentage: Float,
    fontSize: TextUnit,
    radius: Dp,
    color: Color = Color.Black,
    strokeWidth: Dp = 5.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {

    var animatedPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animatedPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDelay),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        animatedPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * 2f), contentAlignment = Alignment.Center
    ) {
        val circleBgColor = MaterialTheme.colorScheme.primary

        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawCircle(
                color = circleBgColor,
                alpha = 0.6f
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * (currentPercentage.value / 10),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = "%.1f".format(currentPercentage.value),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                shadow = Shadow(Color.Blue, blurRadius = 8f, offset = Offset(4f, 4f))
            )
        )

    }
}