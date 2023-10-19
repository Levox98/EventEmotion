package com.eventemotion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.eventemotion.base_ui.theme.AppTheme
import com.eventemotion.feature_event.addEvent
import com.eventemotion.feature_main.addMain


@Composable
fun AppScreen() {
    val navController = rememberNavController()

    AppTheme {
        NavHost(navController = navController, startDestination = "MainRoot") {
            addMain(navController)
            addEvent()
        }
    }
}