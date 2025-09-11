package com.mkj.sooooorrrrryyyyy.ui.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.ui.screens.drawHeartShape
import kotlin.math.cos
import kotlin.math.sin


/**
 * Individual Small Heart for Revolving Animation
 * Each heart pulses individually and rotates around center
 */
@Composable
fun SmallRevolvingHeartLove(
    color: Color,
    angle: Float,
    radius: Dp,
    index: Int,
    modifier: Modifier = Modifier,
) {
    // Individual heart pulsing animation - FIXED timing
    val heartScale by animateFloatAsState(
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600 + (index * 80), // Faster, staggered timing
                easing = EaseInOutCubic
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_pulse_$index"
    )

    // Calculate position based on angle and radius
    val xOffset = (cos(Math.toRadians(angle.toDouble())) * radius.value).dp
    val yOffset = (sin(Math.toRadians(angle.toDouble())) * radius.value).dp

    Canvas(
        modifier = modifier
            .offset(x = xOffset, y = yOffset)
            .scale(heartScale)
    ) {
        drawHeartShape(color, center, size.minDimension * 0.4f)
    }
}