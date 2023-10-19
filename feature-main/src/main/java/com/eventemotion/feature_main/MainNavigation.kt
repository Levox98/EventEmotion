package com.eventemotion.feature_main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eventemotion.core_navigation.MainNavScreen
import com.eventemotion.feature_main.screen.MainScreen

fun NavGraphBuilder.addMain(navController: NavHostController) {
    navigation(
        startDestination = MainNavScreen.Main.route,
        route = MainNavScreen.Root.route
    ) {
        composable(
            route = MainNavScreen.Main.route
        ) {
            MainScreen(
                vm = hiltViewModel(),
                navController = navController
            )
        }
    }
}