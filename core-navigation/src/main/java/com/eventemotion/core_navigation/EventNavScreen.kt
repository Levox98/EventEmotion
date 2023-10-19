package com.eventemotion.core_navigation


sealed class EventNavScreen {
    abstract val route: String

    data object Root : EventNavScreen() {
        override val route: String
            get() = "EventRoot"
    }

    data object CreateEvent : EventNavScreen() {
        override val route: String
            get() = "CreateEvent"
    }
}
