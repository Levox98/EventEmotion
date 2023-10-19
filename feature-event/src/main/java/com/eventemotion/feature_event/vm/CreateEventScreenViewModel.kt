package com.eventemotion.feature_event.vm

import androidx.lifecycle.viewModelScope
import com.eventemotion.common.BaseAction
import com.eventemotion.common.BaseViewModel
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_event.usecase.AddEventEntryUseCase
import com.eventemotion.feature_event.usecase.DeleteEventEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    object CreateCreateEventEntryAction : CreateEventScreenAction()
}

sealed class CreateEventScreenEvent {
    data object CreateCreateEventEntryEvent : CreateEventScreenEvent()
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
            is CreateEventScreenEvent.CreateCreateEventEntryEvent -> CreateEventScreenAction.CreateCreateEventEntryAction
        }
    }

    fun changeName(s: String) {
        viewState = viewState.copy(eventName = s)
    }

    fun changeFeeling(s: String) {
        viewState = viewState.copy(eventFeeling = s)
    }

    fun changeThought(s: String) {
        viewState = viewState.copy(eventThought = s)
    }

    fun createEventEntry() {
        if (viewState.eventName.isNotEmpty() && viewState.eventFeeling.isNotEmpty() && viewState.eventThought.isNotEmpty()) {
            viewModelScope.launch {
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