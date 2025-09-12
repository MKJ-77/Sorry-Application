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