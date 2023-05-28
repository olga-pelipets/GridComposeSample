package com.example.common_ui.borders

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.base.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BordersViewModel @Inject constructor(
) : ViewModel(), LifecycleObserver {

    private val uiEvents = UiEvents<Event>()
    val events: Flow<Event> = uiEvents.events()

    fun sendEvent(event: Event){
        uiEvents.post(event)
    }

    sealed class Event {
    }
}
