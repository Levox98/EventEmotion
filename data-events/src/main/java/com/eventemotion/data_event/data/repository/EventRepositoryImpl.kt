package com.eventemotion.data_event.data.repository

import com.eventemotion.common.Either
import com.eventemotion.data_event.data.db.EventsDao
import com.eventemotion.data_event.data.mapper.toData
import com.eventemotion.data_event.data.mapper.toDomain
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.data_event.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao
) : EventRepository {
    override suspend fun addEvent(eventEntry: EventEntry) {
        eventsDao.addEventEntry(eventEntry.toData())
    }

    override suspend fun deleteEvent(date: Date) = eventsDao.deleteEventEntry(date.time)

    override suspend fun getEvent(date: Long?): Flow<Either<EventEntry>> = flow {
        emit(Either.loading())

        val result = if (date != null) eventsDao.getEventEntry(date).toDomain() else EventEntry()

        emit(Either.success(result))
    }

    override suspend fun getAllEventsFlow() = eventsDao.getEventEntriesFlow().map { list -> list.map { it.toDomain() } }
}