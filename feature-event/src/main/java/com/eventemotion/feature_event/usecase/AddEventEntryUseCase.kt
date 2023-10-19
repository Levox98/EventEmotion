package com.eventemotion.feature_event.usecase

import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.data_event.domain.repository.EventRepository
import javax.inject.Inject

class AddEventEntryUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(event: EventEntry) = repository.addEvent(event)
}