package com.mkj.sooooorrrrryyyyy.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

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