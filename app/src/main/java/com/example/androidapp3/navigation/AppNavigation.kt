package com.example.androidapp3.navigation

import androidx.compose.runtime.Composable
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

    val navController = rememberNavController()

    val treasureHuntViewModel: TreasureHuntViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(
                onStartClick = {

                    treasureHuntViewModel.resetGame()

                    navController.navigate("hunt")
                }
            )
        }

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

        composable("progress") {

            ProgressScreen(
                viewModel = treasureHuntViewModel,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("finish") {

            FinishScreen(
                totalLocations = treasureHuntViewModel.businesses.size,
                onRestartClick = {

                    treasureHuntViewModel.resetGame()

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