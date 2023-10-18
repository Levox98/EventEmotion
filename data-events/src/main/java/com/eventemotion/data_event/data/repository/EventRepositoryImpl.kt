package com.eventemotion.data_event.data.repository

import com.eventemotion.data_event.data.db.EventsDao
import com.eventemotion.data_event.data.mapper.toData
import com.eventemotion.data_event.data.mapper.toDomain
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.data_event.domain.repository.EventRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao
) : EventRepository {
    override suspend fun addEvent(eventEntry: EventEntry) {
        eventsDao.addEventEntry(eventEntry.toData())
    }

    override fun deleteEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEventsFlow() = eventsDao.getEventEntriesFlow().map { list -> list.map { it.toDomain() } }
}