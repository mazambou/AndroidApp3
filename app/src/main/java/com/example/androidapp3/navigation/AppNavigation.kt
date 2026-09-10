package com.example.androidapp3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidapp3.screens.FinishScreen
import com.example.androidapp3.screens.HomeScreen
import com.example.androidapp3.screens.HuntScreen
import com.example.androidapp3.screens.ProgressScreen
import com.example.androidapp3.viewmodel.TreasureHuntViewModel

@Composable
fun AppNavigation() {

    // Navigation controller
    val navController = rememberNavController()

    // Android context used for SharedPreferences
    val context = LocalContext.current

    // Shared ViewModel used by all screens
    val treasureHuntViewModel: TreasureHuntViewModel = viewModel()

    // Load saved progress when the application starts
    LaunchedEffect(Unit) {
        treasureHuntViewModel.loadProgress(context)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // --------------------------------------------------
        // HOME SCREEN
        // --------------------------------------------------

        composable("home") {

            HomeScreen(
                onStartClick = {

                    /*
                     * Do NOT reset the game here.
                     *
                     * If the user already played before,
                     * the saved progress will be preserved.
                     */

                    navController.navigate("hunt")
                }
            )
        }

        // --------------------------------------------------
        // TREASURE HUNT SCREEN
        // --------------------------------------------------

        composable("hunt") {

            HuntScreen(
                viewModel = treasureHuntViewModel,

                onProgressClick = {
                    navController.navigate("progress")
                },

                onFinish = {
                    navController.navigate("finish")
                }
            )
        }

        // --------------------------------------------------
        // PROGRESS SCREEN
        // --------------------------------------------------

        composable("progress") {

            ProgressScreen(
                viewModel = treasureHuntViewModel,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // --------------------------------------------------
        // FINISH SCREEN
        // --------------------------------------------------

        composable("finish") {

            FinishScreen(
                totalLocations = treasureHuntViewModel.businesses.size,

                onRestartClick = {

                    // Reset progress in ViewModel
                    // and delete saved SharedPreferences
                    treasureHuntViewModel.resetGame(context)

                    // Return to Home Screen
                    navController.navigate("home") {

                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}