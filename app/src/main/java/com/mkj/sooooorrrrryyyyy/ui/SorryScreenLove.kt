package com.mkj.sooooorrrrryyyyy.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.ui.components.*
import kotlinx.coroutines.delay

@Composable
fun SorryScreenLove() {
    var apologyText by remember { mutableStateOf("") }
    var showHeartCard by remember { mutableStateOf(false) }
    var showTextInHeart by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8BBD0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Animated hearts at top
            Row {
                repeat(5) {
                    AnimatedHeartLove(
                        modifier = Modifier
                            .size(56.dp)
                            .padding(4.dp),
                        color = Color.Red,
                        fast = it % 2 == 0
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Input field for custom message
            OutlinedTextField(
                value = apologyText,
                onValueChange = { apologyText = it },
                label = { Text("Type your love apology here...") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Button to show heart card with message
            Button(
                onClick = {
                    showHeartCard = true
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Send My Love Apology ❤️")
            }

            Spacer(Modifier.height(40.dp))

            // Heart-shaped card that appears first, then text appears inside
            if (showHeartCard && apologyText.isNotEmpty()) {
                HeartCardWithSequentialTextLove(
                    text = apologyText,
                    onHeartAppeared = { showTextInHeart = true }
                )
            }
        }
    }
}

@Composable
fun HeartCardWithSequentialTextLove(
    text: String,
    onHeartAppeared: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var heartVisible by remember { mutableStateOf(false) }
    var heartFullyVisible by remember { mutableStateOf(false) }

    // Heart appearance animation
    val heartScale by animateFloatAsState(
        targetValue = if (heartVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        finishedListener = {
            if (heartVisible) {
                heartFullyVisible = true
                onHeartAppeared()
            }
        },
        label = "heart_appear"
    )

    // Heart pulsing animation after it appears
    val heartPulse by animateFloatAsState(
        targetValue = if (heartFullyVisible) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_pulse"
    )

    LaunchedEffect(Unit) {
        delay(500) // Small delay before heart appears
        heartVisible = true
    }

    Box(
        modifier = modifier
            .size(300.dp, 270.dp)
            .scale(heartScale * heartPulse),
        contentAlignment = Alignment.Center
    ) {
        HeartShapedCardLove(
            text = if (heartFullyVisible) text else "",
            backgroundColor = Color(0xFFFFE4E6),
            textColor = Color(0xFFAD1457),
            borderColor = Color(0xFFE91E63),
            showTextImmediately = heartFullyVisible
        )
    }
}

// Updated animated hearts for decoration
@Composable
fun AnimatedHeartLove(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    fast: Boolean = false,
) {
    val scale by animateFloatAsState(
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (fast) 400 else 1200, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_scale"
    )

    val rotation by animateFloatAsState(
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (fast) 2000 else 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "heart_rotation"
    )

    Canvas(modifier = modifier.scale(scale)) {
        rotate(rotation) {
            drawHeartLove(
                color = color,
                center = center,
                size = size.minDimension * 0.4f
            )
        }
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHeartLove(
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