package com.mkj.sooooorrrrryyyyy.ui.component


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.ui.theme.HeartRed
import kotlinx.coroutines.delay

@Composable
fun AnimatedHeart(
    modifier: Modifier = Modifier,
    color: Color = HeartRed,
    initialDelay: Long = 0
) {
    var isVisible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "heart_scale"
    )

    val rotation by animateFloatAsState(
        targetValue = if (isVisible) 0f else -45f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "heart_rotation"
    )

    val pulse by animateFloatAsState(
        targetValue = if (isVisible) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_pulse"
    )

    LaunchedEffect(Unit) {
        delay(initialDelay)
        isVisible = true
    }

    Canvas(
        modifier = modifier.size(60.dp)
    ) {
        scale(scale * pulse) {
            drawHeart(
                color = color,
                center = center,
                size = size.minDimension * 0.4f
            )
        }
    }
}

private fun DrawScope.drawHeart(color: Color, center: Offset, size: Float) {
    val path = Path().apply {
        val width = size
        val height = size

        // Heart shape path
        moveTo(center.x, center.y + height * 0.3f)

        cubicTo(
            center.x - width * 0.5f, center.y - height * 0.1f,
            center.x - width * 0.5f, center.y - height * 0.3f,
            center.x, center.y - height * 0.1f
        )

        cubicTo(
            center.x + width * 0.5f, center.y - height * 0.3f,
            center.x + width * 0.5f, center.y - height * 0.1f,
            center.x, center.y + height * 0.3f
        )

        close()
    }

    drawPath(path, color)
}
