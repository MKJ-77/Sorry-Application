package com.mkj.sooooorrrrryyyyy.ui.components.animated_component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.ui.components.SmallRevolvingHeartLove
import com.mkj.sooooorrrrryyyyy.ui.screens.CenterSparkleLove

/**
 * Revolving Hearts Component
 * Creates 6 colorful small hearts that revolve in a circle
 * Each heart has different colors and rotation speeds
 */
@Composable
fun RevolvingHeartsLove(
    modifier: Modifier = Modifier,
    heartCount: Int = 6,
) {
    // FIXED: Continuous rotation animation that actually works
    val infiniteTransition = rememberInfiniteTransition(label = "revolving_hearts")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing), // 3 second rotation
            repeatMode = RepeatMode.Restart
        ),
        label = "circle_rotation"
    )

    // Heart colors
    val heartColors = listOf(
        Color(0xFFE91E63), // Pink
        Color(0xFFFF5722), // Red-Orange
        Color(0xFF9C27B0), // Purple
        Color(0xFFFF9800), // Orange
        Color(0xFFF44336), // Red
        Color(0xFF673AB7)  // Deep Purple
    )

    Box(
        modifier = modifier
            .size(160.dp) // Reduced size
            .rotate(rotation), // FIXED: Apply rotation to the entire container
        contentAlignment = Alignment.Center
    ) {
        // Create hearts positioned in a circle
        repeat(heartCount) { index ->
            val angle = (360f / heartCount) * index
            val radiusOffset = 60.dp // Reduced radius

            SmallRevolvingHeartLove(
                color = heartColors[index % heartColors.size],
                angle = angle,
                radius = radiusOffset,
                index = index,
                modifier = Modifier.size(20.dp) // Reduced heart size
            )
        }

        // Center sparkle effect
        CenterSparkleLove()
    }
}