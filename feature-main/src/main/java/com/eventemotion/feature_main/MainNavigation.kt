package com.eventemotion.feature_main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eventemotion.core_navigation.MainNavScreen

fun NavGraphBuilder.addMain() {
    navigation(
        startDestination = MainNavScreen.Main.route,
        route = MainNavScreen.Root.route
    ) {
        composable(
            route = MainNavScreen.Main.route
        ) {
            //TODO: Add MainScreen composable here
        }
    }
}