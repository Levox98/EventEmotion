package com.eventemotion.feature_event.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.eventemotion.common.BaseAction
import com.eventemotion.common.BaseViewModel
import com.eventemotion.core_navigation.EventNavScreen
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_event.usecase.AddEventEntryUseCase
import com.eventemotion.feature_event.usecase.DeleteEventEntryUseCase
import com.eventemotion.feature_event.usecase.GetEventEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class EventScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val currentEventEntry: EventEntry? = null
)

sealed class EventScreenAction : BaseAction() {
    object GoToNextEntryAction : EventScreenAction()
    object GoToPreviousEntryAction : EventScreenAction()
}

sealed class EventScreenEvent {
    data object GetEventEntryEvent : EventScreenEvent()
}

@HiltViewModel
class EventScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addEventEntryUseCase: AddEventEntryUseCase,
    private val deleteEventEntryUseCase: DeleteEventEntryUseCase,
    private val getEventEntryUseCase: GetEventEntryUseCase
) : BaseViewModel<EventScreenState, EventScreenAction, EventScreenEvent>(
    initialState = EventScreenState(isError = false, isLoading = false, currentEventEntry = null)
) {

    private val eventDate: String? = savedStateHandle[EventNavScreen.Event.EVENT_DATE]

    init {
        obtainEvent(EventScreenEvent.GetEventEntryEvent)
    }

    override fun obtainEvent(viewEvent: EventScreenEvent) {
        when (viewEvent) {
            is EventScreenEvent.GetEventEntryEvent -> getEventEntry()
        }
    }

    private fun getEventEntry() {
        viewModelScope.launch(Dispatchers.IO) {
            if (eventDate != "null") {
                getEventEntryUseCase(eventDate!!.toLong()).collect { result ->
                    withContext(Dispatchers.Main) {
                        when {
                            result.isLoading() -> viewState =
                                viewState.copy(isLoading = true, isError = false)

                            result.isError() -> viewState =
                                viewState.copy(isLoading = false, isError = true)

                            result.isSuccess() -> viewState = viewState.copy(
                                isLoading = false,
                                isError = false,
                                currentEventEntry = result.data
                            )
                        }
                    }
                }
            } else {
                //TODO: Open CreateEventScreen
            }
        }
    }
}