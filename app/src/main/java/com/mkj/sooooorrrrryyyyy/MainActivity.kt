package com.mkj.sooooorrrrryyyyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mkj.sooooorrrrryyyyy.ui.SorryScreen
import com.mkj.sooooorrrrryyyyy.ui.SorryScreenLove
import com.mkj.sooooorrrrryyyyy.ui.theme.SooooorrrrryyyyyTheme
import com.mkj.sooooorrrrryyyyy.ui.theme.SorryAppThemeLove

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SorryAppThemeLove {
                SorryForLove()
            }
        }
    }
}

@Composable
fun SorryForLove() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SorryScreenLove()
    }
}

@Composable
fun SorryForFriend() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SorryScreen()
    }
}