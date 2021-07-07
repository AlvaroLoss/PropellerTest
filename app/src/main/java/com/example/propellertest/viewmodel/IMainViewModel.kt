package com.example.propellertest.viewmodel

import com.example.propellertest.api.model.EventsItem
import kotlinx.coroutines.flow.StateFlow

interface IMainViewModel {
    val eventsFlow: StateFlow<List<EventsItem>>
}
