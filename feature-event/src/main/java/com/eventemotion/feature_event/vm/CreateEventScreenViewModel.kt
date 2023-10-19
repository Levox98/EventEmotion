package com.eventemotion.feature_event.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.eventemotion.common.BaseAction
import com.eventemotion.common.BaseViewModel
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_event.usecase.AddEventEntryUseCase
import com.eventemotion.feature_event.usecase.DeleteEventEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

data class CreateEventScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val eventName: String = "",
    val eventFeeling: String = "",
    val eventThought: String = ""
)

sealed class CreateEventScreenAction : BaseAction() {
    object CreateEventEntryAction : CreateEventScreenAction()
}

sealed class CreateEventScreenEvent {
    data object CreateEventEntryEvent : CreateEventScreenEvent()
}

@HiltViewModel
class CreateEventScreenViewModel @Inject constructor(
    private val addEventEntryUseCase: AddEventEntryUseCase,
    private val deleteEventEntryUseCase: DeleteEventEntryUseCase
) : BaseViewModel<CreateEventScreenState, CreateEventScreenAction, CreateEventScreenEvent>(
    initialState = CreateEventScreenState()
) {

    override fun obtainEvent(viewEvent: CreateEventScreenEvent) {
        when (viewEvent) {
            is CreateEventScreenEvent.CreateEventEntryEvent -> {
                Log.d("create_event_vm", "create")
                sendAction(CreateEventScreenAction.CreateEventEntryAction)
            }
        }
    }

    fun changeName(s: String) {
        viewState = viewState.copy(eventName = s, eventThought = viewState.eventThought, eventFeeling = viewState.eventFeeling)
    }

    fun changeFeeling(s: String) {
        viewState = viewState.copy(eventFeeling = s, eventThought = viewState.eventThought, eventName = viewState.eventName)
    }

    fun changeThought(s: String) {
        viewState = viewState.copy(eventThought = s, eventName = viewState.eventName, eventFeeling = viewState.eventFeeling)
    }

    fun createEventEntry() {
        Log.d("create_event_vm_create", "$viewState")
        if (viewState.eventName.isNotEmpty() && viewState.eventFeeling.isNotEmpty() && viewState.eventThought.isNotEmpty()) {
            Log.d("create_event_vm_", "can create")
            viewModelScope.launch(Dispatchers.IO) {
                addEventEntryUseCase(
                    EventEntry(
                        name = viewState.eventName,
                        feeling = viewState.eventFeeling,
                        thought = viewState.eventThought,
                        date = Date()
                    )
                )
            }
        }
    }
}