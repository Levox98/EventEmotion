package com.eventemotion.core_navigation

import java.util.Date

sealed class EventNavScreen {
    abstract val route: String

    data object Root : EventNavScreen() {
        override val route: String
            get() = "EventRoot"
    }

    data object Event : EventNavScreen() {
        const val EVENT_DATE = "eventDate"

        override val route: String
            get() = "Event/{$EVENT_DATE}"

        fun createRoute(eventDate: Date?) = "Event/${eventDate?.time}"
    }
}
