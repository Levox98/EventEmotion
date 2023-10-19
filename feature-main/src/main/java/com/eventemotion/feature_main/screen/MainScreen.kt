package com.eventemotion.feature_main.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eventemotion.core_navigation.EventNavScreen
import com.eventemotion.data_event.domain.entity.EventEntry
import com.eventemotion.feature_main.vm.MainScreenAction
import com.eventemotion.feature_main.vm.MainScreenEvent
import com.eventemotion.feature_main.vm.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(vm: MainScreenViewModel, navController: NavHostController) {

    val viewStates = vm.viewStates.collectAsState()
    val viewActions = vm.viewActions

    val entries = viewStates.value.eventEntries

    LaunchedEffect(viewActions) {
        viewActions.onEach { action ->
            when (action) {
                is MainScreenAction.GoToCreateEventEntryAction -> {
                    navController.navigate(EventNavScreen.CreateEvent.route)
                }
            }
        }.collect()
    }

    Scaffold(
        floatingActionButton = {
            AddEventButton(
                onClick = remember {
                    {
                        vm.obtainEvent(MainScreenEvent.GoToCreateEntryEvent)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(
                    items = entries,
                    key = { index, item -> "${item.date}/$index" }
                ) { index, item ->
                    EventCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(
                                when (index) {
                                    0 -> Modifier.padding(top = paddingValues.calculateTopPadding())
                                    entries.lastIndex -> Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                                    else -> Modifier
                                }
                            ),
                        data = item,
                        onClick = remember(item) {
                            {
                                //vm.obtainEvent(MainScreenEvent.GoToCreateEntryEvent)
                            }
                        },
                        onDeleteClick = remember(item) {
                            {
                                vm.obtainEvent(MainScreenEvent.DeleteEntryEvent(item))
                            }
                        }
                    )

                    if (index < entries.lastIndex) {
                        Spacer(modifier = Modifier.requiredHeight(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun EventCard(
    modifier: Modifier = Modifier,
    data: EventEntry,
    onClick: (EventEntry) -> Unit,
    onDeleteClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                enabled = true,
                onClick = remember {
                    {
                        onClick(data)
                    }
                }
            )
            .border(1.dp, MaterialTheme.colorScheme.onBackground, MaterialTheme.shapes.small),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(10.dp).padding(start = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${data.date}",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.requiredHeight(5.dp))
                Text(
                    text = data.feeling,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.requiredHeight(5.dp))
                Text(
                    text = data.thought,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        enabled = true,
                        onClick = onDeleteClick
                    ),
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AddEventButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
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
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}