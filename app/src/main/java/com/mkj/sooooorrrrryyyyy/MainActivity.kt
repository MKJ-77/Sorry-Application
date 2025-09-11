package com.mkj.sooooorrrrryyyyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mkj.sooooorrrrryyyyy.ui.NavigationGraph
import com.mkj.sooooorrrrryyyyy.ui.Routes
import com.mkj.sooooorrrrryyyyy.ui.screens.LoveDisplayScreenLove
import com.mkj.sooooorrrrryyyyy.ui.screens.SorryScreenFriend
import com.mkj.sooooorrrrryyyyy.ui.screens.LoveInputScreenLove
import com.mkj.sooooorrrrryyyyy.ui.theme.SorryAppThemeLove

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SorryAppThemeLove {
                NavigationGraph(navController = rememberNavController() , startDestination = Routes.Love.route)
            }
        }
    }
}


@Composable
@Preview
fun LoveApologyAppLove() {
    // Navigation state - true shows input screen, false shows display screen
    var showInputScreen by remember { mutableStateOf(true) }

    // State to hold all user messages
    var loveMessages by remember { mutableStateOf(LoveMessages()) }

    if (showInputScreen) {
        // Input Screen - where user types their messages
        LoveInputScreenLove(
            onNavigateToLove = { messages ->
                // When love button is pressed, save messages and navigate
                loveMessages = messages
                showInputScreen = false // Switch to display screen
            }
        )
    } else {
        // Display Screen - shows the romantic apology with animations
        LoveDisplayScreenLove(
            messages = loveMessages
        )
    }
}

@Composable
fun SorryForFriend() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SorryScreenFriend()
    }
}