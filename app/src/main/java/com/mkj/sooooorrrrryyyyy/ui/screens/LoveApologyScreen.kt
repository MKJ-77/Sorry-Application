package com.mkj.sooooorrrrryyyyy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mkj.sooooorrrrryyyyy.LoveMessages
import com.mkj.sooooorrrrryyyyy.ui.components.BigHeartCardWithMessageLove
import com.mkj.sooooorrrrryyyyy.ui.components.animated_component.TextCards.AnimatedNicknameMessageLove
import com.mkj.sooooorrrrryyyyy.ui.components.animated_component.TextCards.AnimatedShortApologyLove
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
fun LoveApologyScreen(
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

            if (showRevolvingHearts) {
                RevolvingHeartsLove(
                    modifier = Modifier.padding(vertical = 20.dp) // Reduced spacing
                )
            }

            Spacer(modifier = Modifier.height(6.dp)) // Was 24.dp

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




