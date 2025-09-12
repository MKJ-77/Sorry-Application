package com.mkj.sooooorrrrryyyyy.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mkj.sooooorrrrryyyyy.LoveMessages
import com.mkj.sooooorrrrryyyyy.SorryForFriend
import com.mkj.sooooorrrrryyyyy.ui.screens.ChooseApologyLove
import com.mkj.sooooorrrrryyyyy.ui.screens.LoveApologyScreen
import com.mkj.sooooorrrrryyyyy.ui.screens.LoveInputScreenLove

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Routes.ChoosingScreen.route,
) {
    var loveMessages by remember { mutableStateOf(LoveMessages()) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.ChoosingScreen.route) {
            ChooseApologyLove(
                onChooseLover = {
                    navController.navigate(Routes.LoverInput.route) {
                        popUpTo(Routes.ChoosingScreen.route) { inclusive = false }
                    }
                },
                onChooseFriend = {
                    navController.navigate(Routes.FriendScreen.route) {
                        popUpTo(Routes.ChoosingScreen.route) { inclusive = false }
                    }
                },
            )
        }
        composable(Routes.FriendScreen.route) {
            SorryForFriend()
        }
        composable(Routes.LoverInput.route) {
            // State to hold all user messages
            LoveInputScreenLove(
                onNavigateToLove = { messages ->
                    // When love button is pressed, save messages and navigate
                    loveMessages = messages
                    navController.navigate(Routes.LoverScreen.route) {
                        popUpTo(Routes.LoverInput.route) { inclusive = false }
                    }
                }
            )
        }
        composable(Routes.LoverScreen.route) {
            LoveApologyScreen(
                messages = loveMessages
            )
        }

    }

}
