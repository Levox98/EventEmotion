package com.eventemotion.feature_main.usecase

import com.eventemotion.data_event.domain.repository.EventRepository
import java.util.Date
import javax.inject.Inject

class DeleteEventEntryUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(eventDate: Date) = repository.deleteEvent(eventDate)
}