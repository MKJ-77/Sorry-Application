package com.mkj.sooooorrrrryyyyy.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.sooooorrrrryyyyy.LoveMessages
import kotlinx.coroutines.delay

@Composable
fun LoveInputScreenLove(
    onNavigateToLove: (LoveMessages) -> Unit, // Callback to navigate with messages
    modifier: Modifier = Modifier
) {
    // State variables to hold user inputs
    var deepMessage by remember { mutableStateOf("") }
    var shortApology by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }

    // Animation states for input fields appearance
    var showFirstField by remember { mutableStateOf(false) }
    var showSecondField by remember { mutableStateOf(false) }
    var showThirdField by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    // Scroll state for the screen
    val scrollState = rememberScrollState()

    // Sequential animation to show fields one by one
    LaunchedEffect(Unit) {
        delay(500)
        showFirstField = true
        delay(800)
        showSecondField = true
        delay(800)
        showThirdField = true
        delay(600)
        showButton = true
    }

    // Background with romantic gradient
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFCE4EC), // Light pink at top
                        Color(0xFFF8BBD0), // Medium pink in middle
                        Color(0xFFF48FB1)  // Deeper pink at bottom
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Title with romantic styling
            Text(
                text = "Pour Your Heart Out 💕",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAD1457),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // First Input Field - Deep Message (appears first)
            AnimatedInputFieldLove(
                value = deepMessage,
                onValueChange = { deepMessage = it },
                label = "Your Deep Heartfelt Message 💝",
                placeholder = "Express your deepest feelings and apology here...",
                isVisible = showFirstField,
                singleLine = false,
                maxLines = 6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Second Input Field - Short Apology (appears second)
            AnimatedInputFieldLove(
                value = shortApology,
                onValueChange = { shortApology = it },
                label = "Short & Crisp Apology 🥺",
                placeholder = "I'm sorry for...",
                isVisible = showSecondField,
                singleLine = true,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Third Input Field - Nickname (appears third)
            AnimatedInputFieldLove(
                value = nickname,
                onValueChange = { nickname = it },
                label = "Their Special Name 😍",
                placeholder = "Baby, Sweetheart, Love...",
                isVisible = showThirdField,
                singleLine = true,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Love Button - appears last and is enabled only when all fields are filled
            AnimatedLoveButtonLove(
                onClick = {
                    // Navigate to love screen with all messages
                    onNavigateToLove(
                        LoveMessages(
                            deepMessage = deepMessage,
                            shortApology = shortApology,
                            nickname = nickname
                        )
                    )
                },
                isVisible = showButton,
                isEnabled = deepMessage.isNotBlank() &&
                        shortApology.isNotBlank() &&
                        nickname.isNotBlank(),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Helper text
            if (showButton) {
                Text(
                    text = if (deepMessage.isNotBlank() && shortApology.isNotBlank() && nickname.isNotBlank()) {
                        "Tap the heart to send your love! 💖"
                    } else {
                        "Fill all fields to unlock the love button 💕"
                    },
                    fontSize = 12.sp,
                    color = Color(0xFF880E4F),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

/**
 * Animated Input Field Component - FIXED VERSION
 * Slides in and scales up when becoming visible
 * Provides beautiful styling for romantic theme
 */
@Composable
fun AnimatedInputFieldLove(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isVisible: Boolean,
    singleLine: Boolean,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    // Animation for field appearance
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "field_scale"
    )

    // State for focus tracking (simplified)
    var isFocused by remember { mutableStateOf(false) }

    // Animation for field glow effect when focused
    val glowAlpha by animateFloatAsState(
        targetValue = if (isFocused) 0.3f else 0.1f,
        animationSpec = tween(300),
        label = "glow_alpha"
    )

    Box(
        modifier = modifier.scale(scale)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    color = Color(0xFFAD1457),
                    fontWeight = FontWeight.Medium
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF880E4F).copy(alpha = 0.6f)
                )
            },
            singleLine = singleLine,
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE91E63),
                unfocusedBorderColor = Color(0xFFAD1457).copy(alpha = 0.7f),
                focusedLabelColor = Color(0xFFAD1457),
                unfocusedLabelColor = Color(0xFF880E4F),
                cursorColor = Color(0xFFE91E63)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = glowAlpha),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

/**
 * Animated Love Button Component
 * Pulses with heart shape and romantic colors
 * Only enabled when all fields are filled
 */
@Composable
fun AnimatedLoveButtonLove(
    onClick: () -> Unit,
    isVisible: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    // Button appearance animation
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "button_scale"
    )

    // Pulsing animation for enabled button
    val pulse by animateFloatAsState(
        targetValue = if (isEnabled && isVisible) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "button_pulse"
    )

    // Color animation based on enabled state
    val buttonColor by animateColorAsState(
        targetValue = if (isEnabled) Color(0xFFE91E63) else Color(0xFFBDBDBD),
        animationSpec = tween(400),
        label = "button_color"
    )

    Button(
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFBDBDBD),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        shape = RoundedCornerShape(25.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isEnabled) 8.dp else 2.dp,
            pressedElevation = 2.dp
        ),
        modifier = modifier
            .scale(scale * pulse)
            .size(width = 200.dp, height = 56.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "💖 Send Love 💖",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}