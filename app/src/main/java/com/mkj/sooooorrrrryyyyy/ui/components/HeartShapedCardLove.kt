package com.mkj.sooooorrrrryyyyy.ui.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
    showTextImmediately: Boolean = false,
) {
    Box(
        modifier = modifier
            .size(440.dp, 340.dp)
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
        // Position text in upper center area of heart
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.TopCenter // Changed from Center to TopCenter
            ) {
                if (showTextImmediately && text.isNotEmpty()) {
                    TypewriterTextInHeartLove(
                        text = text,
                        textColor = textColor,
                        typingDelayMs = 40,
                        startDelay = 600,
                        // Better positioning for upper center of heart
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 50.dp,    // Start from upper area
                                start = 40.dp,  // Side padding
                                end = 40.dp,    // Side padding
                                bottom = 80.dp  // Leave space at bottom
                            )
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

// FIXED Custom Heart Shape with better proportions for text
val HeartShape = GenericShape { size, _ ->
    val width = size.width
    val height = size.height

    // Optimized heart shape for better text area
    moveTo(width * 0.5f, height * 0.1f)

    // Left curve of heart
    cubicTo(
        width * 0.15f, height * 0.0f,
        width * 0.0f, height * 0.35f,
        width * 0.5f, height * 0.75f
    )

    // Right curve of heart
    cubicTo(
        width * 1.0f, height * 0.35f,
        width * 0.85f, height * 0.0f,
        width * 0.5f, height * 0.1f
    )

    close()
}

// Helper function to create heart path for Canvas drawing
fun createHeartPath(size: androidx.compose.ui.geometry.Size): Path {
    val path = Path()
    val width = size.width
    val height = size.height

    path.moveTo(width * 0.5f, height * 0.1f)

    // Left curve
    path.cubicTo(
        width * 0.15f, height * 0.0f,
        width * 0.0f, height * 0.35f,
        width * 0.5f, height * 0.75f
    )

    // Right curve
    path.cubicTo(
        width * 1.0f, height * 0.35f,
        width * 0.85f, height * 0.0f,
        width * 0.5f, height * 0.1f
    )

    path.close()
    return path
}

@Composable
fun TypewriterTextInHeartLove(
    text: String,
    textColor: Color = Color(0xFFAD1457),
    typingDelayMs: Long = 40,
    startDelay: Long = 0,
    modifier: Modifier = Modifier,
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

        while (true) {
            delay(500)
            showCursor = !showCursor
        }
    }

    // FIXED: Perfect center alignment
    Text(
        text = displayedText + when {
            !typingFinished -> "💖"
            showCursor -> "💖"
            else -> ""
        },
        color = textColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center, // Ensure center alignment
        lineHeight = 18.sp,
        overflow = TextOverflow.Visible,
        modifier = modifier
    )
}

// Sparkle effects around the heart
@Composable
fun SparkleEffectsLove(
    modifier: Modifier = Modifier,
) {
    val sparklePositions = remember {
        listOf(
            0.15f to 0.1f,   // Top left
            0.85f to 0.15f,  // Top right
            0.1f to 0.4f,    // Mid left
            0.9f to 0.45f,   // Mid right
            0.25f to 0.8f,   // Bottom left
            0.75f to 0.85f   // Bottom right
        )
    }

    sparklePositions.forEachIndexed { index, (x, y) ->
        SparkleAnimationLove(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
                .offset(
                    x = (320.dp * x),
                    y = (280.dp * y)
                ),
            delay = index * 200L
        )
    }
}

@Composable
fun SparkleAnimationLove(
    modifier: Modifier = Modifier,
    delay: Long = 0,
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_scale"
    )

    val alpha by animateFloatAsState(
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sparkle_alpha"
    )

    Canvas(
        modifier = modifier
            .size(10.dp)
            .scale(scale)
    ) {
        drawCircle(
            color = Color(0xFFFFD700).copy(alpha = alpha),
            radius = size.minDimension / 2
        )
        drawCircle(
            color = Color.White.copy(alpha = alpha * 0.8f),
            radius = size.minDimension / 3
        )
    }
}