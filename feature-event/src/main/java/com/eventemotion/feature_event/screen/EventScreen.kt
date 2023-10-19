package com.eventemotion.feature_event.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.eventemotion.feature_event.vm.EventScreenViewModel

@Composable
fun EventScreen(vm: EventScreenViewModel, ) {
    Box() {
        Text(text = "Hello world!")
    }
}