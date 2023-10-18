package com.eventemotion.feature_event

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eventemotion.core_navigation.EventNavScreen

fun NavGraphBuilder.addEvent() {
    navigation(
        startDestination = EventNavScreen.Event.route,
        route = EventNavScreen.Root.route
    ) {
        composable(
            route = EventNavScreen.Event.route
        ) {
            //TODO: Add EventScreen composable here
        }
    }
}