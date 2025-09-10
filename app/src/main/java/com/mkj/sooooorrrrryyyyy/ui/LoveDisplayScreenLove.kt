package com.mkj.sooooorrrrryyyyy.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.sooooorrrrryyyyy.LoveMessages
import com.mkj.sooooorrrrryyyyy.ui.components.*
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin


/**
 * Love Display Screen Component
 * Shows the romantic apology with animations in sequence:
 * 1. Small colorful hearts revolving
 * 2. Big heart card with deep message
 * 3. Short apology message below
 * 4. Personalized greeting with nickname
 */
@Composable
fun LoveDisplayScreenLove(
    messages: LoveMessages, // Contains all three user inputs
    modifier: Modifier = Modifier,
) {
    // Animation sequence states
    var showRevolvingHearts by remember { mutableStateOf(false) }
    var showBigHeartCard by remember { mutableStateOf(false) }
    var showShortApology by remember { mutableStateOf(false) }
    var showNicknameMessage by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    // Sequential animation timing
    LaunchedEffect(Unit) {
        delay(800)          // Initial delay
        showRevolvingHearts = true
        delay(3000)         // Let hearts revolve for 3 seconds
        showBigHeartCard = true
        delay(4000)         // Wait for heart card and deep message to appear
        showShortApology = true
        delay(2000)         // Wait for short apology
        showNicknameMessage = true
    }

    // Romantic gradient background
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFCE4EC), // Light pink center
                        Color(0xFFF8BBD0), // Medium pink
                        Color(0xFFF06292)  // Deeper pink edges
                    ),
                    radius = 800f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Section 1: Small Colorful Revolving Hearts (5-7 hearts)
            if (showRevolvingHearts) {
                RevolvingHeartsLove(
                    modifier = Modifier.padding(vertical = 32.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Section 2: Big Heart Card with Deep Message
            if (showBigHeartCard) {
                BigHeartCardWithMessageLove(
                    deepMessage = messages.deepMessage,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Section 3: Short Apology Message Below Heart
            if (showShortApology && messages.shortApology.isNotBlank()) {
                AnimatedShortApologyLove(
                    shortApology = messages.shortApology,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 4: Personalized Message with Nickname
            if (showNicknameMessage && messages.nickname.isNotBlank()) {
                AnimatedNicknameMessageLove(
                    nickname = messages.nickname,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
        }
    }
}

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
    // Rotation animation for the entire circle
    val rotation by animateFloatAsState(
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "circle_rotation"
    )

    // Colors for different hearts
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
            .size(250.dp)
            .rotate(rotation),
        contentAlignment = Alignment.Center
    ) {
        // Create hearts positioned in a circle
        repeat(heartCount) { index ->
            val angle = (360f / heartCount) * index
            val radiusOffset = 70.dp

            SmallRevolvingHeartLove(
                color = heartColors[index % heartColors.size],
                angle = angle,
                radius = radiusOffset,
                index = index,
                modifier = Modifier.size(65.dp)
            )
        }

        // Center sparkle effect
        CenterSparkleLove()
    }
}

/**
 * Individual Small Heart for Revolving Animation
 * Each heart pulses individually and rotates around center
 */
@Composable
fun SmallRevolvingHeartLove(
    color: Color,
    angle: Float,
    radius: androidx.compose.ui.unit.Dp,
    index: Int,
    modifier: Modifier = Modifier,
) {
    // Individual heart pulsing animation
    val heartScale by animateFloatAsState(
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800 + (index * 100), // Staggered timing
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

/**
 * Center Sparkle Effect for Revolving Hearts
 * Adds a glowing center point with pulsing animation
 */
@Composable
fun CenterSparkleLove() {
    val sparkleScale by animateFloatAsState(
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "center_sparkle"
    )

    Canvas(
        modifier = Modifier
            .size(16.dp)
            .scale(sparkleScale)
    ) {
        drawCircle(
            color = Color(0xFFFFD700), // Gold
            radius = size.minDimension / 2
        )
        drawCircle(
            color = Color.White,
            radius = size.minDimension / 4
        )
    }
}

/**
 * Big Heart Card Component
 * Shows the deep message inside a large heart-shaped card
 * Appears after revolving hearts with scale and glow animation
 */
@Composable
fun BigHeartCardWithMessageLove(
    deepMessage: String,
    modifier: Modifier = Modifier,
) {
    var isVisible by remember { mutableStateOf(false) }

    // Heart card appearance animation
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "big_heart_scale"
    )

    LaunchedEffect(Unit) {
        delay(500) // Small delay before appearing
        isVisible = true
    }

    Box(
        modifier = modifier.scale(scale),
        contentAlignment = Alignment.Center
    ) {
        HeartShapedCardLove(
            text = deepMessage,
            backgroundColor = Color(0xFFFFE4E6),
            textColor = Color(0xFFAD1457),
            borderColor = Color(0xFFE91E63),
            showTextImmediately = isVisible,
            modifier = Modifier.size(320.dp, 280.dp)
        )
    }
}

/**
 * Animated Short Apology Component
 * Displays the short apology message with typewriter effect
 * Styled in a beautiful card below the heart
 */
@Composable
fun AnimatedShortApologyLove(
    shortApology: String,
    modifier: Modifier = Modifier,
) {
    var displayedText by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    // Card appearance animation
    val cardScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "apology_card_scale"
    )

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
        delay(600) // Wait for card to appear

        // Typewriter effect for short apology
        shortApology.forEachIndexed { index, _ ->
            displayedText = shortApology.substring(0, index + 1)
            delay(70)
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .scale(cardScale),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "💔 Quick Apology 💔",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAD1457),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = displayedText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF880E4F),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }
}

/**
 * Animated Nickname Message Component
 * Shows a personalized message using the nickname
 * Appears last with beautiful styling and effects
 */
@Composable
fun AnimatedNicknameMessageLove(
    nickname: String,
    modifier: Modifier = Modifier,
) {
    var displayedText by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    val fullMessage = "I hope you can forgive me, $nickname 💕\nYou mean the world to me! ✨"

    // Message appearance animation
    val messageScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "nickname_scale"
    )

    LaunchedEffect(Unit) {
        delay(400)
        isVisible = true
        delay(700)

        // Typewriter effect for nickname message
        fullMessage.forEachIndexed { index, _ ->
            displayedText = fullMessage.substring(0, index + 1)
            delay(60)
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .scale(messageScale),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFCE4EC)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = displayedText,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFAD1457),
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }
    }
}

/**
 * Helper function to draw heart shapes in Canvas
 * Used by revolving hearts animation
 */
fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHeartShape(
    color: Color,
    center: androidx.compose.ui.geometry.Offset,
    size: Float,
) {
    val path = Path().apply {
        val width = size
        val height = size

        moveTo(center.x, center.y + height * 0.3f)

        // Left curve of heart
        cubicTo(
            center.x - width * 0.5f, center.y - height * 0.1f,
            center.x - width * 0.5f, center.y - height * 0.3f,
            center.x, center.y - height * 0.1f
        )

        // Right curve of heart
        cubicTo(
            center.x + width * 0.5f, center.y - height * 0.3f,
            center.x + width * 0.5f, center.y - height * 0.1f,
            center.x, center.y + height * 0.3f
        )

        close()
    }

    drawPath(path, color)
}