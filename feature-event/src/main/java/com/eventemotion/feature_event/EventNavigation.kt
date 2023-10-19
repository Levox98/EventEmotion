package com.eventemotion.feature_event

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eventemotion.core_navigation.EventNavScreen
import com.eventemotion.feature_event.screen.CreateEventScreen

fun NavGraphBuilder.addEvent(navController: NavHostController) {
    navigation(
        startDestination = EventNavScreen.CreateEvent.route,
        route = EventNavScreen.Root.route
    ) {
        composable(
            route = EventNavScreen.CreateEvent.route
        ) {
            CreateEventScreen(vm = hiltViewModel(), navController = navController)
        }
    }
}