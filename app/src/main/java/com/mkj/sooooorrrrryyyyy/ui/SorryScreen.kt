package com.mkj.sooooorrrrryyyyy.ui

import androidx.compose.animation.core.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.sooooorrrrryyyyy.ui.component.*
import com.mkj.sooooorrrrryyyyy.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SorryScreen() {
    var currentStage by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        repeat(5) { stage ->
            delay(if (stage == 0) 1000 else 3500)
            currentStage = stage + 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftLavender,
                        CalmBlue.copy(alpha = 0.4f),
                        WarmBeige.copy(alpha = 0.6f)
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

            // Stars animation (friendship symbols)
            if (currentStage >= 1) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    repeat(4) { index ->
                        AnimatedStar(
                            initialDelay = index * 300L,
                            color = when (index % 3) {
                                0 -> TrustGold
                                1 -> SincereBlue
                                else -> FriendshipPurple
                            }
                        )
                    }
                }
            }

            // Main title
            if (currentStage >= 2) {
                TypewriterText(
                    text = "I'm Truly Sorry",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = RespectfulGray,
                    startDelay = 500,
                    typingDelayMs = 120,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Main apology message
            if (currentStage >= 3) {
                TypewriterText(
                    text = "I know there's absolutely no excuse for what happened. My reflexive action caused you pain, and I'm deeply ashamed of myself for that moment.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 19.sp,
                        lineHeight = 27.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = RespectfulGray,
                    startDelay = 1000,
                    typingDelayMs = 42,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (currentStage >= 4) {
                TypewriterText(
                    text = "You're an amazing friend who deserved so much better than that. I promise to work on controlling my reflexes and being more mindful around you.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 17.sp,
                        lineHeight = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = RespectfulGray,
                    startDelay = 2200,
                    typingDelayMs = 48,
                    modifier = Modifier.padding(bottom = 28.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Final message with button
            if (currentStage >= 5) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    TypewriterText(
                        text = "I built this app to show you how genuinely sorry I am.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = SincereBlue,
                        startDelay = 600,
                        typingDelayMs = 65,
                        modifier = Modifier.padding(bottom = 28.dp)
                    )

                    PulsatingButton(
                        text = "I Hope You Can Forgive Me 🙏",
                        onClick = { /* Could navigate to another screen or trigger action */ },
                        isVisible = true,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TypewriterText(
                        text = "- Your Sincerely MKJ",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = RespectfulGray.copy(alpha = 0.7f),
                        startDelay = 2000,
                        typingDelayMs = 85,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }
    }
}
