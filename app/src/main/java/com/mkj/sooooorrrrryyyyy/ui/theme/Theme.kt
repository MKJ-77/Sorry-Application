package com.mkj.sooooorrrrryyyyy.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun SooooorrrrryyyyyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}


private val LightColorScheme = lightColorScheme(
    primary = SincereBlue,
    secondary = FriendshipPurple,
    tertiary = TrustGold,
    background = SoftLavender,
    surface = SoftWhite,
    onPrimary = SoftWhite,
    onSecondary = RespectfulGray,
    onTertiary = SoftWhite,
    onBackground = RespectfulGray,
    onSurface = RespectfulGray
)

@Composable
fun SorryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
