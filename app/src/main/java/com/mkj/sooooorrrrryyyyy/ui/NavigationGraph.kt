package com.mkj.sooooorrrrryyyyy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mkj.sooooorrrrryyyyy.ui.screens.ChooseApologyLove

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Routes.ChoosingScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ==================== AUTH SCREENS ====================

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
        composable(Routes.LoverInput.route) {
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
        composable(Routes.LoverScreen.route) {
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

    }

}

//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ) {
//
//        // ==================== AUTH SCREENS ====================
//
//        // Splash Screen - Check if user is logged in
//        composable(Screen.Splash.route) {
//            val viewModel: SplashViewModel = hiltViewModel()
//            val uiState by viewModel.uiState.collectAsState()
//
//            EnhancedSplashScreen (
//                uiState = uiState,
//                onNavigateToLogin = {
//                    navController.navigate(Screen.Login.route) {
//                        popUpTo(Screen.Splash.route) { inclusive = true }
//                    }
//                },
//                onNavigateToLeaderboard = {
//                    navController.navigate(Screen.Leaderboard.route) {
//                        popUpTo(Screen.Splash.route) { inclusive = true }
//                    }
//                }
//            )
//        }


