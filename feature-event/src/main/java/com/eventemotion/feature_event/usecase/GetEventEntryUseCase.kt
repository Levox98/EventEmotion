package com.eventemotion.feature_event.usecase

import com.eventemotion.data_event.domain.repository.EventRepository
import javax.inject.Inject

class GetEventEntryUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(date: Long?) = repository.getEvent(date)
}