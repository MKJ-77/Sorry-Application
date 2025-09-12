package com.mkj.sooooorrrrryyyyy.ui.components.animated_component.TextCards

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

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