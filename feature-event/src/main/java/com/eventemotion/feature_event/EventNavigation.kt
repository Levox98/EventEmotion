package com.eventemotion.feature_event

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eventemotion.core_navigation.EventNavScreen
import com.eventemotion.feature_event.screen.EventScreen

fun NavGraphBuilder.addEvent() {
    navigation(
        startDestination = EventNavScreen.Event.route,
        route = EventNavScreen.Root.route
    ) {
        composable(
            route = EventNavScreen.Event.route
        ) {
            EventScreen(vm = hiltViewModel())
        }
    }
}