package com.mkj.sooooorrrrryyyyy.ui.components


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.mkj.sooooorrrrryyyyy.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Particle(
    val id: Int,
    val startX: Float,
    val startY: Float,
    val color: Color,
    val size: Float,
    val speed: Float,
    val direction: Float,
)

@Composable
fun FloatingParticles(
    modifier: Modifier = Modifier,
    particleCount: Int = 12,
) {
    var particles by remember { mutableStateOf(emptyList<Particle>()) }

    val animationTime by rememberInfiniteTransition(label = "particle_animation").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    LaunchedEffect(particleCount) {
        val colors = listOf(FriendshipPurple, SincereBlue, TrustGold)
        particles = (0 until particleCount).map { i ->
            Particle(
                id = i,
                startX = Random.nextFloat(),
                startY = Random.nextFloat(),
                color = colors[i % colors.size],
                size = Random.nextFloat() * 6f + 3f,
                speed = Random.nextFloat() * 0.3f + 0.1f,
                direction = Random.nextFloat() * 360f
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        particles.forEach { particle ->
            val x = size.width * particle.startX +
                    cos(Math.toRadians((animationTime * particle.speed + particle.direction).toDouble())).toFloat() * 40f
            val y = size.height * particle.startY +
                    sin(Math.toRadians((animationTime * particle.speed + particle.direction).toDouble())).toFloat() * 25f

            drawCircle(
                color = particle.color.copy(alpha = 0.5f),
                radius = particle.size,
                center = Offset(x, y)
            )
        }
    }
}
