package com.mkj.sooooorrrrryyyyy.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.ui.theme.*

@Composable
fun PulsatingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
) {
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "button_scale"
    )

    val pulse by animateFloatAsState(
        targetValue = if (isVisible) 1.04f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "button_pulse"
    )

    Box(
        modifier = modifier
            .scale(scale * pulse)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(SincereBlue, FriendshipPurple)
                )
            )
            .clickable { onClick() }
            .padding(horizontal = 28.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )
    }
}
