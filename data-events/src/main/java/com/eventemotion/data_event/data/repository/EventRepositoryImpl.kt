package com.eventemotion.data_event.data.repository

import com.eventemotion.common.Either
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.data_event.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(

) : EventRepository {
    override fun addEvent(eventEntry: EventEntry) {
        TODO("Not yet implemented")
    }

    override fun deleteEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEventsFlow(): Flow<Either<List<EventEntry>>> {
        TODO("Not yet implemented")
    }
}