package com.mkj.sooooorrrrryyyyy.ui.screens

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.sooooorrrrryyyyy.LoveMessages
import com.mkj.sooooorrrrryyyyy.ui.components.HeartShapedCardLove
import com.mkj.sooooorrrrryyyyy.ui.components.animated_component.RevolvingHeartsLove
import kotlinx.coroutines.delay


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
    messages: LoveMessages,
    modifier: Modifier = Modifier,
) {
    // Animation sequence states
    var showRevolvingHearts by remember { mutableStateOf(false) }
    var showBigHeartCard by remember { mutableStateOf(false) }
    var showShortApology by remember { mutableStateOf(false) }
    var showNicknameMessage by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    // FIXED: Faster animation timing with less delays
    LaunchedEffect(Unit) {
        delay(500)          // Reduced initial delay
        showRevolvingHearts = true
        delay(2500)         // Reduced revolving time to 2.5 seconds
        showBigHeartCard = true
        delay(3000)         // Reduced wait time
        showShortApology = true
        delay(1500)         // Reduced wait time
        showNicknameMessage = true
    }

    // Romantic gradient background
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFCE4EC),
                        Color(0xFFF8BBD0),
                        Color(0xFFF06292)
                    ),
                    radius = 800f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Reduced padding
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // FIXED: Revolving Hearts with proper rotation and reduced spacing
            if (showRevolvingHearts) {
                RevolvingHeartsLove(
                    modifier = Modifier.padding(vertical = 20.dp) // Reduced spacing
                )
            }

            // REDUCED spacing between elements
            Spacer(modifier = Modifier.height(12.dp)) // Was 24.dp

            // Big Heart Card with Deep Message
            if (showBigHeartCard) {
                BigHeartCardWithMessageLove(
                    deepMessage = messages.deepMessage,
                    modifier = Modifier.padding(vertical = 8.dp) // Reduced spacing
                )
            }

            // REDUCED spacing
            Spacer(modifier = Modifier.height(16.dp)) // Was 32.dp

            // Short Apology Message Below Heart
            if (showShortApology && messages.shortApology.isNotBlank()) {
                AnimatedShortApologyLove(
                    shortApology = messages.shortApology,
                    modifier = Modifier.padding(vertical = 8.dp) // Reduced spacing
                )
            }

            // REDUCED spacing
            Spacer(modifier = Modifier.height(12.dp)) // Was 24.dp

            // Personalized Message with Nickname
            if (showNicknameMessage && messages.nickname.isNotBlank()) {
                AnimatedNicknameMessageLove(
                    nickname = messages.nickname,
                    modifier = Modifier.padding(vertical = 12.dp) // Reduced spacing
                )
            }
        }
    }
}



/**
 * Center Sparkle Effect for Revolving Hearts
 * Adds a glowing center point with pulsing animation
 */
@Composable
fun CenterSparkleLove() {
    val sparkleScale by animateFloatAsState(
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "center_sparkle"
    )

    Canvas(
        modifier = Modifier
            .size(14.dp)
            .scale(sparkleScale)
    ) {
        drawCircle(
            color = Color(0xFFFFD700),
            radius = size.minDimension / 2
        )
        drawCircle(
            color = Color.White,
            radius = size.minDimension / 3
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

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium // Slightly faster animation
        ),
        label = "big_heart_scale"
    )

    LaunchedEffect(Unit) {
        delay(300) // Reduced delay
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
            modifier = Modifier.size(300.dp, 260.dp)
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

    val cardScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "apology_card_scale"
    )

    LaunchedEffect(Unit) {
        delay(200)
        isVisible = true
        delay(400)

        shortApology.forEachIndexed { index, _ ->
            displayedText = shortApology.substring(0, index + 1)
            delay(50)
        }
    }

    // FIXED: Perfect centering for the entire card
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Center the entire card
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f) // Card takes 90% width
                .scale(cardScale),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            // FIXED: No padding issues - perfectly centered content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), // Equal padding on all sides
                horizontalAlignment = Alignment.CenterHorizontally // Center all content
            ) {
                Text(
                    text = "💔 Quick Apology 💔",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFAD1457),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = displayedText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF880E4F),
                    textAlign = TextAlign.Center, // Center text alignment
                    lineHeight = 20.sp,
                    modifier = Modifier.fillMaxWidth() // Take full width for centering
                )
            }
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

    val messageScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "nickname_scale"
    )

    LaunchedEffect(Unit) {
        delay(200)
        isVisible = true
        delay(500)

        fullMessage.forEachIndexed { index, _ ->
            displayedText = fullMessage.substring(0, index + 1)
            delay(45)
        }
    }

    // FIXED: Perfect centering for the entire card
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Center the entire card
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f) // Card takes 95% width
                .scale(messageScale),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFCE4EC)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            // FIXED: No padding issues - perfectly centered content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), // Equal padding on all sides
                horizontalAlignment = Alignment.CenterHorizontally // Center all content
            ) {
                Text(
                    text = displayedText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFAD1457),
                    textAlign = TextAlign.Center, // Center text alignment
                    lineHeight = 24.sp,
                    modifier = Modifier.fillMaxWidth() // Take full width for centering
                )
            }
        }
    }
}

/**
 * Helper function to draw heart shapes in Canvas
 * Used by revolving hearts animation
 */
fun DrawScope.drawHeartShape(
    color: Color,
    center: Offset,
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