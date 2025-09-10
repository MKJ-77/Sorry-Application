package com.mkj.sooooorrrrryyyyy.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.sooooorrrrryyyyy.ui.component.*
import com.mkj.sooooorrrrryyyyy.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SorryScreenLove() {
    var currentStage by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        repeat(5) { stage ->
            delay(if (stage == 0) 1000 else 3000)
            currentStage = stage + 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftPink,
                        GentleBlue.copy(alpha = 0.3f),
                        SoftPink
                    )
                )
            )
    ) {
        // Floating particles background
        FloatingParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Hearts animation
            if (currentStage >= 1) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    repeat(5) { index ->
                        AnimatedHeart(
                            initialDelay = index * 200L,
                            color = when (index % 3) {
                                0 -> HeartRed
                                1 -> DeepRose
                                else -> WarmPink
                            }
                        )
                    }
                }
            }

            // Main title
            if (currentStage >= 2) {
                TypewriterText(
                    text = "I'm Deeply Sorry",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = WarmGray,
                    startDelay = 500,
                    typingDelayMs = 100,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Apology message
            if (currentStage >= 3) {
                TypewriterText(
                    text = "I know there's no excuse for what happened. My reflex action caused you pain, and I'm truly ashamed of myself.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        lineHeight = 28.sp
                    ),
                    color = WarmGray,
                    startDelay = 1000,
                    typingDelayMs = 40,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (currentStage >= 4) {
                TypewriterText(
                    text = "You didn't deserve that, and I promise to be more mindful. Your friendship means everything to me, and I hope you can find it in your heart to forgive me.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp,
                        lineHeight = 26.sp
                    ),
                    color = WarmGray,
                    startDelay = 2000,
                    typingDelayMs = 45,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Final message with button
            if (currentStage >= 5) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    TypewriterText(
                        text = "I made this app to show you how sorry I truly am.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = SorrowBlue,
                        startDelay = 500,
                        typingDelayMs = 60,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    PulsatingButton(
                        text = "I Hope You Can Forgive Me ❤️",
                        onClick = { /* Could show another screen or send a message */ },
                        isVisible = true,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    TypewriterText(
                        text = "- Your truly sorry friend",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        ),
                        color = WarmGray.copy(alpha = 0.7f),
                        startDelay = 1500,
                        typingDelayMs = 80,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}
