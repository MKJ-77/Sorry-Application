package com.mkj.sooooorrrrryyyyy.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = MaterialTheme.colorScheme.onBackground,
    typingDelayMs: Long = 50,
    startDelay: Long = 0
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        delay(startDelay)
        displayedText = ""
        text.forEachIndexed { index, _ ->
            displayedText = text.substring(0, index + 1)
            delay(typingDelayMs)
        }
    }

    Text(
        text = displayedText + if (displayedText.length < text.length) "|" else "",
        modifier = modifier,
        style = style.copy(fontWeight = FontWeight.Medium),
        color = color
    )
}
