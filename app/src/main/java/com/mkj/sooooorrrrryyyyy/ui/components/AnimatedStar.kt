package com.mkj.sooooorrrrryyyyy.ui.components


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
import com.mkj.sooooorrrrryyyyy.ui.theme.TrustGold
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedStar(
    modifier: Modifier = Modifier,
    color: Color = TrustGold,
    initialDelay: Long = 0
) {
    var isVisible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "star_scale"
    )

    val rotation by animateFloatAsState(
        targetValue = if (isVisible) 360f else 0f,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
        label = "star_rotation"
    )

    val twinkle by animateFloatAsState(
        targetValue = if (isVisible) 1.2f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "star_twinkle"
    )

    LaunchedEffect(Unit) {
        delay(initialDelay)
        isVisible = true
    }

    Canvas(
        modifier = modifier.size(50.dp)
    ) {
        scale(scale * twinkle) {
            drawStar(
                color = color,
                center = center,
                radius = size.minDimension * 0.3f,
                rotation = rotation
            )
        }
    }
}

private fun DrawScope.drawStar(
    color: Color,
    center: Offset,
    radius: Float,
    rotation: Float = 0f
) {
    val path = Path()
    val points = 5
    val outerRadius = radius
    val innerRadius = radius * 0.4f

    for (i in 0 until points * 2) {
        val angle = (i * Math.PI / points) + Math.toRadians(rotation.toDouble())
        val currentRadius = if (i % 2 == 0) outerRadius else innerRadius

        val x = center.x + (currentRadius * cos(angle)).toFloat()
        val y = center.y + (currentRadius * sin(angle)).toFloat()

        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }

    path.close()
    drawPath(path, color)
}
