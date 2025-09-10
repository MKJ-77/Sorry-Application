package com.mkj.sooooorrrrryyyyy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LovePink = Color(0xFFF8BBD0)
val LoveRed = Color(0xFFD32F2F)
val DeepRoseLove = Color(0xFFE57373)
val SoftWhiteLove = Color(0xFFFFFBFE)
val DarkLoveGray = Color(0xFF616161)

private val LoveColorScheme = lightColorScheme(
    primary = LoveRed,
    secondary = DeepRoseLove,
    background = LovePink,
    surface = SoftWhiteLove,
    onPrimary = SoftWhiteLove,
    onSecondary = SoftWhiteLove,
    onBackground = DarkLoveGray,
    onSurface = DarkLoveGray
)

@Composable
fun SorryAppThemeLove(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LoveColorScheme,
        typography = TypographyLove,
        content = content
    )
}

val TypographyLove = androidx.compose.material3.Typography()