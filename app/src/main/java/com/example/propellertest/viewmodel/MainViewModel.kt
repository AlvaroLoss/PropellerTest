package com.example.propellertest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.api.model.EventsItem
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val apiServiceProvider: ApiServiceProvider
): ViewModel(), IMainViewModel {
    override val eventsFlow: StateFlow<List<EventsItem>>
        get() = apiServiceProvider.getEventsByDate()
}