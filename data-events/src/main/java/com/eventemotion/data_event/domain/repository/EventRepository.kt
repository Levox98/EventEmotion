package com.eventemotion.data_event.domain.repository

import com.eventemotion.common.Either
import com.eventemotion.data_event.domain.entity.EventEntry
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun addEvent(eventEntry: EventEntry)
    fun deleteEvent()
    suspend fun getAllEventsFlow(): Flow<Either<List<EventEntry>>>
}