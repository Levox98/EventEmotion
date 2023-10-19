package com.eventemotion.data_event.domain.repository

import com.eventemotion.data_event.domain.entity.EventEntry
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface EventRepository {
    suspend fun addEvent(eventEntry: EventEntry)
    suspend fun deleteEvent(date: Date)
    suspend fun getAllEventsFlow(): Flow<List<EventEntry>>
}