package com.eventemotion.data_event.domain.entity

import java.util.Date

data class EventEntry(
    val name: String = "",
    val feeling: String = "",
    val thought: String = "",
    val date: Date = Date()
)
