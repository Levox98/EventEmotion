package com.eventemotion.feature_event.vm

import com.eventemotion.common.BaseAction
import com.eventemotion.common.BaseViewModel
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_event.usecase.AddEventEntryUseCase
import com.eventemotion.feature_event.usecase.DeleteEventEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val currentEventEntry: EventEntry? = null
)

sealed class EventScreenAction : BaseAction() {
    object CreateEventEntryAction : EventScreenAction()
}

sealed class EventScreenEvent {
    data object CreateEventEntryEvent : EventScreenEvent()
}

@HiltViewModel
class CreateEventScreenViewModel @Inject constructor(
    private val addEventEntryUseCase: AddEventEntryUseCase,
    private val deleteEventEntryUseCase: DeleteEventEntryUseCase
) : BaseViewModel<EventScreenState, EventScreenAction, EventScreenEvent>(
    initialState = EventScreenState(isError = false, isLoading = false, currentEventEntry = null)
) {

    override fun obtainEvent(viewEvent: EventScreenEvent) {
        when (viewEvent) {
            is EventScreenEvent.CreateEventEntryEvent -> createEventEntry()
        }
    }

    private fun createEventEntry() {
        //TODO
    }
}