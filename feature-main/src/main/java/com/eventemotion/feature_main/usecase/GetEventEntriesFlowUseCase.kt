package com.eventemotion.feature_main.usecase

import com.eventemotion.data_event.domain.repository.EventRepository
import javax.inject.Inject

class GetEventEntriesFlowUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke() = repository.getAllEventsFlow()
}