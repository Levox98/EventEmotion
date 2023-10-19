package com.eventemotion.feature_main.vm

import androidx.lifecycle.viewModelScope
import com.eventemotion.common.BaseAction
import com.eventemotion.common.BaseViewModel
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_main.usecase.DeleteEventEntryUseCase
import com.eventemotion.feature_main.usecase.GetEventEntriesFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class MainScreenState(
    val isError: Boolean,
    val loading: Boolean,
    val eventEntries: List<EventEntry>
)

sealed class MainScreenAction : BaseAction() {
    data object GoToCreateEventEntryAction : MainScreenAction()
}

sealed class MainScreenEvent {
    data object GetEntriesFlowEvent : MainScreenEvent()
    data object GoToCreateEntryEvent : MainScreenEvent()
    class DeleteEntryEvent(val entry: EventEntry) : MainScreenEvent()
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getEventEntriesFlowUseCase: GetEventEntriesFlowUseCase,
    private val deleteEventEntryUseCase: DeleteEventEntryUseCase
) : BaseViewModel<MainScreenState, MainScreenAction, MainScreenEvent>(
    initialState = MainScreenState(isError = false, loading = false, eventEntries = emptyList())
) {

    init {
        obtainEvent(MainScreenEvent.GetEntriesFlowEvent)
    }

    override fun obtainEvent(viewEvent: MainScreenEvent) {
        when (viewEvent) {
            is MainScreenEvent.GetEntriesFlowEvent -> getEventEntriesFlow()
            is MainScreenEvent.GoToCreateEntryEvent -> sendAction(MainScreenAction.GoToCreateEventEntryAction)
            is MainScreenEvent.DeleteEntryEvent -> deleteEventEntry(viewEvent.entry)
        }
    }

    private fun getEventEntriesFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            getEventEntriesFlowUseCase().collect {
                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        isError = false,
                        loading = false,
                        eventEntries = it.sortedByDescending { it.date }
                    )
                }
            }
        }
    }

    private fun deleteEventEntry(eventEntry: EventEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteEventEntryUseCase(eventEntry.date)
        }
    }
}