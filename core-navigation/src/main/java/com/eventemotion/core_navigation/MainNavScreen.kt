package com.eventemotion.core_navigation

sealed class MainNavScreen {
    abstract val route: String

    data object Root : MainNavScreen() {
        override val route: String
            get() = "MainRoot"
    }

    data object Main : MainNavScreen() {
        override val route: String
            get() = "Main"
    }
}
