package com.mkj.sooooorrrrryyyyy.ui.screens

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/**
 * ChooseApology Screen Component
 * Beautiful themed cards for Lover (romantic) and Friend (calm/pleasant) apologies
 * Each card has unique animations and styling matching its theme
 */
@Composable
fun ChooseApologyLove(
    onChooseLover: () -> Unit,
    onChooseFriend: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Animated background gradient that shifts colors
    val infiniteTransition = rememberInfiniteTransition(label = "background_animation")

    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradient_shift"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF8E1).copy(alpha = 0.8f + 0.2f * gradientOffset),
                        Color(0xFFF3E5F5).copy(alpha = 0.9f + 0.1f * gradientOffset),
                        Color(0xFFE8F5E8).copy(alpha = 0.8f + 0.2f * gradientOffset)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //  : Animated title with typewriter effect
            AnimatedTitleLove()

            Spacer(modifier = Modifier.height(48.dp))

            //  : Lover apology card with all animations working
            RomanticLoverCardLove(
                onClick = onChooseLover,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            //  : Friend apology card with all animations working
            CalmFriendCardLove(
                onClick = onChooseFriend
            )
        }
    }
}


@Composable
fun AnimatedTitleLove() {
    var displayedText by remember { mutableStateOf("") }
    val fullText = "Choose Your Apology Style"

    // Title scale animation
    val titleScale by animateFloatAsState(
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "title_scale"
    )

    LaunchedEffect(Unit) {
        fullText.forEachIndexed { index, _ ->
            displayedText = fullText.substring(0, index + 1)
            kotlinx.coroutines.delay(100)
        }
    }

    Text(
        text = displayedText,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF424242),
        textAlign = TextAlign.Center,
        modifier = Modifier.scale(titleScale)
    )
}

/**
 * Romantic Lover Card Component
 * Features: Hearts, pink gradients, pulsing animations, romantic colors
 */
@Composable
fun RomanticLoverCardLove(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    //  : Proper infinite animation setup
    val infiniteTransition = rememberInfiniteTransition(label = "lover_card_animations")

    // Pulsing animation for romantic effect
    val heartPulse by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "lover_pulse"
    )

    // Rotation animation for the entire card
    val cardRotation by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "card_rotation"
    )

    // Hover effect simulation
    var isPressed by remember { mutableStateOf(false) }
    val cardScale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "lover_scale",
        finishedListener = { if (isPressed) isPressed = false }
    )

    Card(
        modifier = modifier
            .size(280.dp)
            .scale(cardScale * heartPulse)
            .rotate(cardRotation)
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                isPressed = true
                onClick()
            },
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFE4E6),
                            Color(0xFFF8BBD0),
                            Color(0xFFE91E63)
                        ),
                        radius = 400f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Animated floating hearts
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 20.dp)
                ) {
                    FloatingHeartsLove()
                }

                Text(
                    text = "💕 For My Lover 💕",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFAD1457),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Express deep romantic\nfeelings and passion",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF880E4F),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }

            // Sparkle effects
//            SparkleOverlayLove()
        }
    }
}

/**
 * Calm Friend Card Component
 * Features: Stars, soft blues/greens, gentle animations, peaceful colors
 */
@Composable
fun CalmFriendCardLove(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Proper infinite animation setup
    val infiniteTransition = rememberInfiniteTransition(label = "friend_card_animations")

    // Gentle breathing animation
    val calmPulse by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "friend_pulse"
    )

    // Gentle swaying animation
    val cardSway by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "card_sway"
    )

    var isPressed by remember { mutableStateOf(false) }
    val cardScale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "friend_scale",
        finishedListener = { if (isPressed) isPressed = false }
    )

    Card(
        modifier = modifier
            .size(280.dp)
            .scale(cardScale * calmPulse)
            .rotate(cardSway)
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                isPressed = true
                onClick()
            },
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE8F5E8),
                            Color(0xFFE3F2FD),
                            Color(0xFFF1F8E9)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //  : Rotating stars
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 16.dp)
                ) {
                    GentleStarsLove ()
                }

                Text(
                    text = "🌟 For My Friend 🌟",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Show sincere respect\nand genuine care",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1B5E20),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }

            //  : Wave effects
            WaveOverlayLove ()
        }
    }
}

/**
 * Floating Hearts Animation for Lover Card
 * Creates multiple hearts that float and pulse romantically
 */
@Composable
fun FloatingHeartsLove() {
    val heartPositions = remember {
        listOf(
            0.2f to 0.3f, 0.8f to 0.2f, 0.5f to 0.7f,
            0.1f to 0.6f, 0.9f to 0.8f
        )
    }

    heartPositions.forEachIndexed { index, (x, y) ->
        AnimatedHeartFloatLove(
            x = x,
            y = y,
            delay = index * 200L,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun AnimatedHeartFloatLove(
    x: Float,
    y: Float,
    delay: Long,
    modifier: Modifier = Modifier,
) {
    //  : Use rememberInfiniteTransition for proper infinite animation
    val infiniteTransition = rememberInfiniteTransition(label = "heart_float_$x$y")

    val floatY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000 + delay.toInt(), easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_float"
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000 + delay.toInt(), easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_scale"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500 + delay.toInt(), easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_rotation"
    )

    Canvas(
        modifier = modifier
            .size(12.dp)
            .offset(
                x = (80.dp * x),
                y = (80.dp * y) + floatY.dp
            )
            .scale(scale)
    ) {
        rotate(rotation) {
            drawHeartLoveForChoosingScreen(
                color = Color(0xFFE91E63),
                center = center,
                size = size.minDimension * 0.8f
            )
        }
    }
}

/**
 * Gentle Stars Animation for Friend Card
 * Creates soft twinkling stars with calm rotation
 */
@Composable
fun GentleStarsLove() {
    val infiniteTransition = rememberInfiniteTransition(label = "stars_rotation")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "star_rotation"
    )

    val twinkle by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "star_twinkle"
    )

    val starPositions = remember {
        listOf(
            0.3f to 0.2f, 0.7f to 0.3f, 0.2f to 0.7f,
            0.8f to 0.8f, 0.5f to 0.5f
        )
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        rotate(rotation) {
            starPositions.forEachIndexed { index, (x, y) ->
                drawStarLove(
                    color = Color(0xFF4CAF50).copy(alpha = 0.6f + 0.4f * twinkle),
                    center = androidx.compose.ui.geometry.Offset(
                        size.width * x,
                        size.height * y
                    ),
                    radius = 6f + (index * 2f) + 2f * twinkle
                )
            }
        }
    }
}

/**
 * Sparkle Overlay for Lover Card
 * Adds romantic sparkles around the edges
 */

@Composable
fun SparkleOverlayLove() {
    val sparklePositions = remember {
        listOf(
            0.1f to 0.1f, 0.9f to 0.15f, 0.05f to 0.5f,
            0.95f to 0.6f, 0.2f to 0.9f, 0.8f to 0.85f
        )
    }

    sparklePositions.forEachIndexed { index, (x, y) ->
        SparkleAnimationLove(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (250.dp * x), y = (250.dp * y)),
            delay = index * 300L,
            color = Color(0xFFFFD700)
        )
    }
}

@Composable
fun SparkleAnimationLove(
    modifier: Modifier = Modifier,
    delay: Long = 0,
    color: Color = Color(0xFFFFD700),
) {
    val infiniteTransition = rememberInfiniteTransition(label = "sparkle_$delay")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_alpha"
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = .8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_scale"
    )

    Canvas(
        modifier = modifier
            .size(8.dp)
            .scale(scale)
    ) {
        drawCircle(
            color = color.copy(alpha = alpha),
            radius = size.minDimension / 2
        )
        // Add inner sparkle
        drawCircle(
            color = Color.White.copy(alpha = alpha * 0.8f),
            radius = size.minDimension / 4
        )
    }
}

/**
 * Wave Overlay for Friend Card
 * Adds gentle wave effects for calm vibes
 */
@Composable
fun WaveOverlayLove() {
    val infiniteTransition = rememberInfiniteTransition(label = "waves")

    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * kotlin.math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave_phase"
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val waveHeight = 15f
        val waveLength = size.width / 3

        repeat(3) { index ->
            val path = Path()
            val y = size.height * (0.2f + index * 0.3f)

            path.moveTo(0f, y)
            for (x in 0..size.width.toInt() step 8) {
                val waveY = y + waveHeight * kotlin.math.sin(
                    (x / waveLength) * 2 * kotlin.math.PI + wavePhase + (index * 0.5f)
                ).toFloat()
                path.lineTo(x.toFloat(), waveY)
            }

            drawPath(
                path = path,
                color = Color(0xFF81C784).copy(alpha = 0.4f + 0.2f * kotlin.math.sin(wavePhase + index)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx())
            )
        }
    }
}


// Helper functions for drawing shapes
fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHeartLoveForChoosingScreen(
    color: Color,
    center: androidx.compose.ui.geometry.Offset,
    size: Float,
) {
    val path = Path().apply {
        val width = size
        val height = size

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

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawStarLove(
    color: Color,
    center: androidx.compose.ui.geometry.Offset,
    radius: Float,
) {
    val path = Path()
    val points = 5
    val outerRadius = radius
    val innerRadius = radius * 0.4f

    for (i in 0 until points * 2) {
        val angle = (i * kotlin.math.PI / points)
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