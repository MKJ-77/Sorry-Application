package com.mkj.sooooorrrrryyyyy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Heart-shaped Card component that shows text after heart appears
@Composable
fun HeartShapedCardLove(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFFFE4E6),
    textColor: Color = Color(0xFFAD1457),
    borderColor: Color = Color(0xFFE91E63),
    showTextImmediately: Boolean = false
) {
    Box(
        modifier = modifier
            .size(280.dp, 250.dp)
            .clip(HeartShape)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                if (showTextImmediately && text.isNotEmpty()) {
                    TypewriterTextInHeartLove(
                        text = text,
                        textColor = textColor,
                        typingDelayMs = 60,
                        startDelay = 800 // Wait a bit after heart appears
                    )
                }
            }
        }

        // Animated heart border outline
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val path = createHeartPath(size)
            drawPath(
                path = path,
                color = borderColor,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5.dp.toPx())
            )
        }

        // Sparkle effects around the heart
        SparkleEffectsLove(
            modifier = Modifier.fillMaxSize()
        )
    }
}

// Custom Heart Shape for clipping the card
val HeartShape = GenericShape { size, _ ->
    val width = size.width
    val height = size.height

    // Create heart path - optimized for text display
    moveTo(width * 0.5f, height * 0.2f)

    // Left curve of heart
    cubicTo(
        width * 0.15f, height * 0.05f,
        width * 0.05f, height * 0.35f,
        width * 0.5f, height * 0.7f
    )

    // Right curve of heart
    cubicTo(
        width * 0.95f, height * 0.35f,
        width * 0.85f, height * 0.05f,
        width * 0.5f, height * 0.2f
    )

    close()
}

// Helper function to create heart path for Canvas drawing
fun createHeartPath(size: androidx.compose.ui.geometry.Size): Path {
    val path = Path()
    val width = size.width
    val height = size.height

    path.moveTo(width * 0.5f, height * 0.2f)

    // Left curve
    path.cubicTo(
        width * 0.15f, height * 0.05f,
        width * 0.05f, height * 0.35f,
        width * 0.5f, height * 0.7f
    )

    // Right curve
    path.cubicTo(
        width * 0.95f, height * 0.35f,
        width * 0.85f, height * 0.05f,
        width * 0.5f, height * 0.2f
    )

    path.close()
    return path
}

// Typewriter text optimized for heart-shaped display
@Composable
fun TypewriterTextInHeartLove(
    text: String,
    textColor: Color = Color(0xFFAD1457),
    typingDelayMs: Long = 60,
    startDelay: Long = 0,
    modifier: Modifier = Modifier
) {
    var displayedText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }
    var typingFinished by remember { mutableStateOf(false) }

    LaunchedEffect(text) {
        delay(startDelay)
        displayedText = ""
        typingFinished = false

        text.forEachIndexed { index, _ ->
            displayedText = text.substring(0, index + 1)
            delay(typingDelayMs)
        }

        typingFinished = true

        // Cursor blinking after typing finishes
        while (true) {
            delay(600)
            showCursor = !showCursor
        }
    }

    Text(
        text = displayedText + when {
            !typingFinished -> "💖"
            showCursor -> "💖"
            else -> ""
        },
        color = textColor,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    )
}

// Sparkle effects around the heart
@Composable
fun SparkleEffectsLove(
    modifier: Modifier = Modifier
) {
    val sparklePositions = remember {
        listOf(
            0.1f to 0.2f,
            0.9f to 0.3f,
            0.2f to 0.8f,
            0.8f to 0.9f,
            0.05f to 0.6f,
            0.95f to 0.7f
        )
    }

    sparklePositions.forEachIndexed { index, (x, y) ->
        SparkleAnimationLove(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
                .offset(
                    x = (280.dp * x),
                    y = (250.dp * y)
                ),
            delay = index * 300L
        )
    }
}

@Composable
fun SparkleAnimationLove(
    modifier: Modifier = Modifier,
    delay: Long = 0
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_scale"
    )

    val alpha by animateFloatAsState(
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_alpha"
    )

    Canvas(
        modifier = modifier
            .size(12.dp)
            .scale(scale)
    ) {
        drawCircle(
            color = Color(0xFFFFD700).copy(alpha = alpha),
            radius = size.minDimension / 2
        )
        drawCircle(
            color = Color.White.copy(alpha = alpha * 0.7f),
            radius = size.minDimension / 4
        )
    }
}