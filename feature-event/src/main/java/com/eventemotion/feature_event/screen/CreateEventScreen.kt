package com.eventemotion.feature_event.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eventemotion.feature_event.vm.CreateEventScreenAction
import com.eventemotion.feature_event.vm.CreateEventScreenEvent
import com.eventemotion.feature_event.vm.CreateEventScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun CreateEventScreen(vm: CreateEventScreenViewModel, navController: NavHostController) {

    val viewStates = vm.viewStates.collectAsState()
    val viewActions = vm.viewActions

    val name = viewStates.value.eventName
    val feeling = viewStates.value.eventFeeling
    val thought = viewStates.value.eventThought

    LaunchedEffect(viewActions) {
        viewActions.onEach { action ->
            when (action) {
                is CreateEventScreenAction.CreateCreateEventEntryAction -> {
                    vm.createEventEntry()
                    navController.popBackStack()
                }
            }
        }.collect()
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        floatingActionButton = {
            SaveEntryButton(
                onClick = remember {{
                    vm.obtainEvent(CreateEventScreenEvent.CreateCreateEventEntryEvent)
                }}
            )
        }
    ) { paddingValues ->
        LazyColumn {
            item {
                TextField(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    value = name,
                    onValueChange = vm::changeName,
                    placeholder = {
                        Text(text = "Что случилось")
                    }
                )
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                TextField(
                    value = feeling,
                    onValueChange = vm::changeFeeling,
                    placeholder = {
                        Text(text = "Что почувствовал")
                    }
                )
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                TextField(
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                    value = thought,
                    onValueChange = vm::changeThought,
                    placeholder = {
                        Text(text = "Что подумал")
                    }
                )
            }
        }
    }
}

@Composable
private fun SaveEntryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
    ) {

    Surface(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                enabled = true,
                onClick = onClick
            ),
        color = MaterialTheme.colorScheme.onBackground,
        shape = MaterialTheme.shapes.large
    ) {
        Icon(
            modifier = Modifier.padding(20.dp),
            imageVector = Icons.Default.Save,
            contentDescription = null
        )
    }
}